package com.una.controllers;

import com.una.dto.ExtraDTO;
import com.una.services.ExtraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/extra")
public class ExtraController {

    private final ExtraService service;

    public ExtraController(ExtraService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<ExtraDTO> getAllExtras() {
        return service.getAllExtras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtraDTO> getExtraById(@PathVariable Integer id) {
        return service.findExtraById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ExtraDTO createExtra(@RequestBody ExtraDTO dto) {
        Optional<ExtraDTO> found = service.findExtraByName(dto.getName());
        if (found.isPresent()) {
            ResponseEntity.badRequest().build();
            return null;
        }
        ResponseEntity.ok();
        return service.createExtra(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExtraDTO> updateExtra(@PathVariable Integer id, @RequestBody ExtraDTO dto) {
        return service.findExtraById(id)
                .map(extra -> {
                    extra.setName(dto.getName());
                    extra.setDescription(dto.getDescription());
                    extra.setPrice(dto.getPrice());
                    return ResponseEntity.ok(service.createExtra(extra));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExtraDTO> deleteExtraById(@PathVariable Integer id) {
        Optional<ExtraDTO> found = service.findExtraById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteExtraById(id);
        return ResponseEntity.ok().build();
    }
}
