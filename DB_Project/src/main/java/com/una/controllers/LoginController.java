package com.una.controllers;

import com.una.dto.LoginDTO;
import com.una.models.User;
import com.una.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/auth")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        Optional<User> found = userRepository.findByUsername(dto.getUsername());
        if (found.isEmpty() || !found.get().getPassword().equals(dto.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        User user = found.get();
        String role = user.getAdmin() != null ? "ADMIN" : "CLIENT";
        return ResponseEntity.ok(role);
    }

    @GetMapping("/whoami")
    public ResponseEntity<String> whoami(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("No autenticado");
        }
        return ResponseEntity.ok("Autenticado como: " + authentication.getName());
    }
}
