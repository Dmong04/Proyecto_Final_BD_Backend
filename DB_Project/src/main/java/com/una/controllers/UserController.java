package com.una.controllers;

import com.una.utils.requests.AdminUserRequest;
import com.una.dto.UserDTO;
import com.una.services.UserService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.ClientUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<UserDTO>>> getAllUsers() {
        try {
            List<UserDTO> users = service.getAllUsers();
            GenericResponse<List<UserDTO>> response = new GenericResponse<>();
            if (users.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de usuarios está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(users, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<UserDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<GenericResponse<UserDTO>> getUserByUsername(@PathVariable String username) {
        try {
            Optional<UserDTO> found = service.findUserByUsername(username);
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El usuario no existe",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(found.get(), true,
                    "Se encontró al usuario en los registros",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Opcional borrar este nomás
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO dto) {
        Optional<UserDTO> found = service.findUserByUsername(dto.getUsername());
        if (found.isPresent()) {
            ResponseEntity.badRequest().build();
            return null;
        }
        return service.createUser(dto);
    }

    @PostMapping("/admin")
    public ResponseEntity<GenericResponse<UserDTO>> createAdminUser(@RequestBody AdminUserRequest request) {
        try {
            Optional<UserDTO> found = service.findUserByUsername(request.getUsername());
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(null, false,
                        "No se pudo ingresar al usuario administrador",
                        HttpStatus.BAD_REQUEST);
            }
            service.saveAdminUser(request.getName(), request.getEmail(), request.getUsername(), request.getPassword());
            return response.buildResponse(null, true,
                    "Usuario administrador ingresado exitosamente",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/client")
    public ResponseEntity<GenericResponse<UserDTO>> createClientUser(@RequestBody ClientUserRequest request) {
        try {
            Optional<UserDTO> found = service.findUserByUsername(request.getUsername());
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(null, false,
                        "No se pudo ingresar al usuario cliente",
                        HttpStatus.BAD_REQUEST);
            }
            service.saveClientUser(request.getName(), request.getPhone(),  request.getEmail(),
                    request.getUsername(), request.getPassword());
            return response.buildResponse(null, true,
                    "Usuario cliente ingresado exitosamente",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO dto) {
        return service.findUserById(id)
                .map(user -> {
                    user.setEmail(dto.getEmail());
                    user.setUsername(dto.getUsername());
                    user.setClient(dto.getClient());
                    user.setAdmin(dto.getAdmin());
                    return ResponseEntity.ok(service.createUser(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Integer id) {
        Optional<UserDTO> found = service.findUserById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
