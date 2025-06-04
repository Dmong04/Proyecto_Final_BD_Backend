package com.una.controllers;

import com.una.dto.UserDTO;
import com.una.services.UserService;
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

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return service.findUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO dto) {
        Optional<UserDTO> found = service.findUserByUsername(dto.getUsername());
        if (found.isPresent()) {
            ResponseEntity.badRequest().build();
            return null;
        }
        return service.createUser(dto);
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
