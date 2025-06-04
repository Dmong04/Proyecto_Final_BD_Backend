package com.una.controllers;

import com.una.dto.LoginDTO;
import com.una.dto.UserDTO;
import com.una.models.User;
import com.una.repositories.UserRepository;
import com.una.security.token.AppToken;
import com.una.security.token.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/auth")
public class LoginController {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public LoginController(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        Optional<User> found = userRepository.findByUsername(dto.getUsername());
        if (found.isEmpty() || !found.get().getPassword().equals(dto.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        User user = found.get();
        String role = user.getAdmin() != null ? "ADMIN" : "CLIENT";

        AppToken token = new AppToken();
        token.setUserId(user.getId());
        token.setUsername(user.getUsername());
        token.setRole(role);
        token.setExpiration(Instant.now().plus(2, ChronoUnit.HOURS));

        return tokenProvider.encrypt(token)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/whoami")
    public ResponseEntity<String> whoami(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("No autenticado");
        }
        return ResponseEntity.ok("Autenticado como: " + authentication.getName());
    }
}
