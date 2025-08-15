package com.una.controllers;

import com.una.dto.AdminDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        try
            {
                return ResponseEntity.ok(service.getAllAdmins());
            } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Integer id) {
        try {
            Optional<AdminDTO> found = service.findAdminById(id);
            return found.map(adminDTO -> new ResponseEntity<>(adminDTO, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AdminDTO> getAdminByName(@PathVariable String name) {
        try {
            Optional<AdminDTO> found =  service.findAdminByName(name);
            return found.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO dto) {
        try {
            Optional<AdminDTO> found = service.findAdminByName(dto.getName());
            if (found.isPresent()) {
                ResponseEntity.badRequest().build();
                return null;
            }
            ResponseEntity.ok();
            return ResponseEntity.ok(service.createAdmin(dto));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Integer id, @RequestBody AdminDTO dto) {
        try {
            return service.findAdminById(id)
                    .map(admin -> {
                        admin.setName(dto.getName());
                        return ResponseEntity.ok(service.createAdmin(admin));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminDTO> deleteAdminById(@PathVariable Integer id) {
        try {
            Optional<AdminDTO> found = service.findAdminById(id);
            if (found.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteAdminById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
