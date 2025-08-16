package com.una.controllers;

import com.una.dto.TourDTO;
import com.una.dto.UserDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.UserService;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            return ResponseEntity.ok(service.getAllUsers());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }

    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        try {
            Optional<UserDTO> found = service.findUserByUsername(username);
            return found.map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        try {
            Optional<UserDTO> found = service.findUserByUsername(dto.getUsername());
            if (found.isPresent()) {
                ResponseEntity.badRequest().build();
                return null;
            }
            return ResponseEntity.ok(service.createUser(dto));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO dto) {
        try {
            return service.findUserById(id)
                    .map(user -> {
                        user.setEmail(dto.getEmail());
                        user.setUsername(dto.getUsername());
                        user.setClient(dto.getClient());
                        user.setAdmin(dto.getAdmin());
                        return ResponseEntity.ok(service.createUser(user));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Integer id) {
        try {
            Optional<UserDTO> found = service.findUserById(id);
            if (found.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
