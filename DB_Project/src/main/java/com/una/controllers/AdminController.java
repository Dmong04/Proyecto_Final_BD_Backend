package com.una.controllers;

import com.una.dto.AdminDTO;
import com.una.services.AdminService;
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

    @GetMapping
    public List<AdminDTO> getAllAdmins() {
        return service.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Integer id) {
        return service.findAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Ejemplo a seguir en los demás endpoints
    @GetMapping("/name/{name}")
    public ResponseEntity<AdminDTO> getAdminByName(@PathVariable String name) {
        try {
            Optional<AdminDTO> found =  service.findAdminByName(name);
            return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public AdminDTO createAdmin(@RequestBody AdminDTO dto) {
        Optional<AdminDTO> found = service.findAdminByName(dto.getName());
        if (found.isPresent()) {
            ResponseEntity.badRequest().build();
            return null;
        }
        ResponseEntity.ok();
        return service.createAdmin(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Integer id, @RequestBody AdminDTO dto) {
        return service.findAdminById(id)
                .map(admin -> {
                    admin.setName(dto.getName());
                    return ResponseEntity.ok(service.createAdmin(admin));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminDTO> deleteAdminById(@PathVariable Integer id) {
        Optional<AdminDTO> found = service.findAdminById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteAdminById(id);
        return ResponseEntity.ok().build();
    }
}
