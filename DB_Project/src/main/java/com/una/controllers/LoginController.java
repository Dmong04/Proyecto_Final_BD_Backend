package com.una.controllers;

import com.una.dto.LoginDTO;
import com.una.dto.LoginResponseDTO;
import com.una.models.User;
import com.una.repositories.UserRepository;
import com.una.security.token.JwtService;
import com.una.utils.GenericResponse;
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
    public ResponseEntity<GenericResponse<LoginResponseDTO>> login(@RequestBody LoginDTO dto) {
        try {
            Optional<User> found = userRepository.findByUsername(dto.getUsername());
            GenericResponse<LoginResponseDTO> response = new GenericResponse<>();
            if (found.isEmpty() || !found.get().getPassword().equals(dto.getPassword())) {
                return response.buildResponse(null, false,
                        "Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
            }
            User user = found.get();
            String token = jwtService.generateToken(user.getUsername(), user.getRole());
            String name;
            if (user.getAdmin() == null) {
                name = user.getClient().getName();
            } else {
                name = user.getAdmin().getName();
            }
            var logged = new LoginResponseDTO(name, user.getUsername(), user.getRole(), token);
            return response.buildResponse(logged, true,
                    "Inicio de sesión exitoso",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<LoginResponseDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para validar al usuario // Solamente de prueba
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
