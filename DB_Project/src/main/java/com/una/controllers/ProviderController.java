package com.una.controllers;

import com.una.dto.ProviderDTO;
import com.una.services.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/provider")
public class ProviderController {

    private final ProviderService service;

    public ProviderController(ProviderService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProviderDTO> getAllProviders() {
        return service.getAllProviders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> getProviderById(@PathVariable Integer id) {
        return service.findProviderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProviderDTO> getProviderByName(@PathVariable String name) {
        return service.findProviderByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProviderDTO createProvider(@RequestBody ProviderDTO dto) {
        Optional<ProviderDTO> found = service.findProviderByName(dto.getName());
        if (found.isPresent()) {
            ResponseEntity.badRequest().build();
            return null;
        }
        ResponseEntity.ok();
        return service.createProvider(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> updateProvider(@PathVariable Integer id, @RequestBody ProviderDTO dto) {
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
    public ResponseEntity<ProviderDTO> deleteProviderById(@PathVariable Integer id) {
        Optional<ProviderDTO> found = service.findProviderById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteProviderById(id);
        return ResponseEntity.ok().build();
    }
}
