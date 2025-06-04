package com.una.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                cors()
                .and()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
//    private final TokenAuthFilter tokenAuthFilter;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize ->
//                        authorize.requestMatchers("/coco_tours/api/v2/auth/**").permitAll()
//                                .requestMatchers("/coco_tours/api/v2/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/coco_tours/api/v2/client/**").hasRole("CLIENT")
//                                .anyRequest().authenticated()
//                ).addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
}
