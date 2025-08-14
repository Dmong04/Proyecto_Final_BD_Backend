package com.una.security.token;

import com.una.models.User;
import com.una.repositories.UserRepository;
import com.una.security.token.routes.APIRoutes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private APIRoutes routes;
    @Autowired
    private PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authPath = request.getServletPath();
        if (authPath.equals("/coco_tours/api/v2/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        String role = null;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Token no proporcionado o formato inválido\"}");
            return;
        }
        token = authHeader.substring(7);
        try {
            username = jwtService.extractUsername(token);
            role = jwtService.extractRole(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Token inválido o expirado\"}");
            return;
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> userOpt = userRepository.findByUsername(username);
            if (userOpt.isPresent() && jwtService.validateToken(token, username)) {
                User user = userOpt.get();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), null, List.of());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        if (!isRouteAllowedForRole(request.getRequestURI(), role)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Acceso denegado para el rol: " + role + "\"}");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isRouteAllowedForRole(String requestURI, String role) {
        List<String> allowedRoutes = "ADMIN".equals(role) ? routes.getAdminRoutes()
                : "CLIENT".equals(role) ? routes.getClientRoutes()
                : List.of();

        return allowedRoutes.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, requestURI));
    };
}
