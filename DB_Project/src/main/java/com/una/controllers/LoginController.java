package com.una.controllers;

import com.una.dto.LoginDTO;
import com.una.dto.LoginResponseDTO;
import com.una.models.User;
import com.una.repositories.UserRepository;
import com.una.security.token.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/auth")
public class LoginController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            Optional<User> found = userRepository.findByUsername(dto.getUsername());
            if (found.isEmpty() || !found.get().getPassword().equals(dto.getPassword())) {
                return ResponseEntity.badRequest().build();
            }
            User user = found.get();
            String token = jwtService.generateToken(user.getUsername(), user.getRole());
            String name;
            if (user.getAdmin() == null) {
                name = user.getClient().getName();
            } else {
                name = user.getAdmin().getName();
            }
            return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), name, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Método para validar al usuario
    @GetMapping("/whoami")
    public ResponseEntity<String> whoami(Authentication authentication) {
        try {
            if (authentication == null) {
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("No autenticado");
            }
            return ResponseEntity.ok("Autenticado como: " + authentication.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
