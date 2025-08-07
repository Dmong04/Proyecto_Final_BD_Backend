package com.una.controllers;

import com.una.dto.SupplierDTO;
import com.una.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/supplier")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<SupplierDTO> getAllProviders() {
        return service.getAllProviders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getProviderById(@PathVariable Integer id) {
        return service.findProviderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplierDTO> getProviderByName(@PathVariable String name) {
        return service.findProviderByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SupplierDTO createProvider(@RequestBody SupplierDTO dto) {
        Optional<SupplierDTO> found = service.findProviderByName(dto.getName());
        if (found.isPresent()) {
            ResponseEntity.badRequest().build();
            return null;
        }
        ResponseEntity.ok();
        return service.createProvider(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateProvider(@PathVariable Integer id, @RequestBody SupplierDTO dto) {
        return service.findProviderById(id)
                .map(provider -> {
                    provider.setName(dto.getName());
                    provider.setDescription(dto.getDescription());
                    provider.setEmail(dto.getEmail());
                    return ResponseEntity.ok(service.createProvider(provider));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDTO> deleteProviderById(@PathVariable Integer id) {
        Optional<SupplierDTO> found = service.findProviderById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteProviderById(id);
        return ResponseEntity.ok().build();
    }
}
