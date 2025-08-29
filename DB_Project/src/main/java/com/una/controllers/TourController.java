package com.una.controllers;

import com.una.dto.TourDTO;
import com.una.services.TourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/tours")
public class TourController {

    private final TourService service;

    public TourController(TourService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<TourDTO> getAllTours() {
        return service.getAllTours();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable Integer id) {
        return service.getTourById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TourDTO createTour(@RequestBody TourDTO dto) {
        Optional<TourDTO> found = service.getTourByType(dto.getType());
        if (found.isPresent()) {
            ResponseEntity.badRequest().build();
            return null;
        }
        service.insertTour(dto);
        return ResponseEntity.ok(dto).getBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourDTO> updateTour(@PathVariable Integer id, @RequestBody TourDTO dto) {
        Optional<TourDTO> found = service.getTourById(id);
        if (found.isPresent()) {
            service.updateTour(dto);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourDTO> deleteTourById(@PathVariable Integer id) {
        Optional<TourDTO> found = service.getTourById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteTourById(id);
        return ResponseEntity.ok().build();
    }
}
