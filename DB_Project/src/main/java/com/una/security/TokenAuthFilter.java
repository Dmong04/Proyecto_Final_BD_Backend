//package com.una.security;
//
//import com.una.security.token.AppToken;
//import com.una.security.token.TokenProvider;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@Log4j2
//@Component
//@RequiredArgsConstructor
//public class TokenAuthFilter extends OncePerRequestFilter {
//
//    private final TokenProvider tokenProvider;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain) throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//        log.info("Authorization header: {}", authHeader);
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String token = authHeader.substring(7);
//        Optional<AppToken> appToken = tokenProvider.decrypt(token);
//
//        if (appToken.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"error\": \"Invalid token\"}");
//            return;
//        }
//
//// Construye las autoridades (roles)
//        String role = "ROLE_" + appToken.get().getRole().toUpperCase(); // Spring espera el prefijo "ROLE_"
//        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
//
//// Crea el Authentication
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                appToken.get().getUsername(),
//                null,
//                authorities
//        );
//
//// Coloca el usuario autenticado en el contexto
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//// También puedes guardar el token si quieres
//        request.setAttribute("appToken", appToken.get());
//
//// Continúa el filtro
//        filterChain.doFilter(request, response);
//    }
//}
