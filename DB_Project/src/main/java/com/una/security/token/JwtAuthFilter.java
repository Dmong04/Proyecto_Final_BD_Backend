
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
import org.springframework.util.AntPathMatcher;
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
    
    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authPath = request.getServletPath();
        if (authPath.equals("/coco_tours/api/v2/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization header: " + (authHeader != null ? authHeader.substring(0, Math.min(20, authHeader.length())) + "..." : "null"));
        
        String token = null;
        String username = null;
        String role = null;
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No valid Authorization header found");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Token no proporcionado o formato inválido\"}");
            return;
        }
        
        token = authHeader.substring(7);
        System.out.println("Token extracted, length: " + token.length());
        
        try {
            username = jwtService.extractUsername(token);
            role = jwtService.extractRole(token);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Token invalido o expirado\"}");
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
            } else {
                System.out.println("User not found or token invalid");
            }
        }
        
        if (!isRouteAllowedForRole(request.getRequestURI(), role)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Acceso denegado para el rol: " + role + "\"}");
            return;
        }
        
        System.out.println("DEBUG - Access GRANTED for role: " + role + " to URI: " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }
    
    private boolean isRouteAllowedForRole(String requestURI, String role) {
        List<String> allowedRoutes = "ADMIN".equals(role) ? routes.adminRoutes
                : "CLIENT".equals(role) ? routes.clientRoutes
                : List.of();

        return allowedRoutes.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, requestURI));
    }
}
