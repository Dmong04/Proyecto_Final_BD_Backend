package com.una.controllers;

import com.una.dto.DetailExtraDTO;
import com.una.services.DetailExtraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/extra_details")
public class DetailExtraController {

    private final DetailExtraService service;

    public DetailExtraController(DetailExtraService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetailExtraDTO> getAllExtraDetails() {
        return service.getAllExtraDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailExtraDTO> getExtraDetailById(@PathVariable Integer id) {
        return service.findExtraDetailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetailExtraDTO createExtraDetail(@RequestBody DetailExtraDTO dto) {
        ResponseEntity.ok();
        return service.createExtraDetail(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailExtraDTO> updateExtraDetail(@PathVariable Integer id, @RequestBody DetailExtraDTO dto) {
        return service.findExtraDetailById(id)
                .map(extraDetail -> {
                    extraDetail.setParticipants(dto.getParticipants());
                    extraDetail.setExtra(dto.getExtra());
                    return ResponseEntity.ok(service.createExtraDetail(extraDetail));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetailExtraDTO> deleteExtraDetailById(@PathVariable Integer id) {
        Optional<DetailExtraDTO> found = service.findExtraDetailById(id);
        if (found.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        service.deleteExtraDetailById(id);
        return ResponseEntity.ok().build();
    }
}
