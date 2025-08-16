package com.una.controllers;

import com.una.dto.AdminDTO;
import com.una.dto.ReservationDTO;
import com.una.dto.SupplierDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.SupplierService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<SupplierDTO>> getAllProviders() {
        try {
            return ResponseEntity.ok(service.getAllProviders());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getProviderById(@PathVariable Integer id) {
        try {
            Optional<SupplierDTO> found = service.findProviderById(id);
            return found.map(supplierDTO -> new ResponseEntity<>(supplierDTO, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplierDTO> getProviderByName(@PathVariable String name) {
        try {
            Optional<SupplierDTO> found =  service.findProviderByName(name);
            return found.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createProvider(@RequestBody SupplierDTO dto) {
        try {
            Optional<SupplierDTO> found = service.findProviderByName(dto.getName());
            if (found.isPresent()) {
                ResponseEntity.badRequest().build();
                return null;
            }
//            ResponseEntity.ok();
            return ResponseEntity.ok(service.createProvider(dto));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateProvider(@PathVariable Integer id, @RequestBody SupplierDTO dto) {
        try {
            return service.findProviderById(id)
                    .map(provider -> {
                        provider.setName(dto.getName());
                        provider.setDescription(dto.getDescription());
                        provider.setEmail(dto.getEmail());
                        return ResponseEntity.ok(service.createProvider(provider));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDTO> deleteProviderById(@PathVariable Integer id) {
        try {
            Optional<SupplierDTO> found = service.findProviderById(id);
            if (found.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteProviderById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }

    }
}
