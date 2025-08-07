package com.una.controllers;

import com.una.dto.DetailTourDTO;
import com.una.services.DetailTourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("coco_tours/api/v2/tour_details")
public class DetailTourController {

    private final DetailTourService service;

    public DetailTourController(DetailTourService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<DetailTourDTO> getAllDetails() {
        return service.getAllDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailTourDTO> getDetailById(@PathVariable Integer id) {
        return service.findDetailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetailTourDTO createDetail(@RequestBody DetailTourDTO dto) {
        if (dto.getTour() == null || dto.getTour().getId() == null) {
         ResponseEntity.badRequest().build();
        }
        ResponseEntity.ok();
        return service.createDetail(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailTourDTO> updateDetail(@PathVariable Integer id, @RequestBody DetailTourDTO dto) {
        return service.findDetailById(id)
                .map(detail -> {
                    detail.setOrigin(dto.getOrigin());
                    detail.setDestination(dto.getDestination());
                    detail.setTour(dto.getTour());
                    detail.setProvider(dto.getProvider());
                    return ResponseEntity.ok(service.createDetail(detail));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetailTourDTO> deleteDetailById(@PathVariable Integer id) {
        if (service.findDetailById(id).isEmpty()) {
            ResponseEntity.badRequest().build();
        }
        service.deleteDetailById(id);
        return ResponseEntity.ok().build();
    }
}
