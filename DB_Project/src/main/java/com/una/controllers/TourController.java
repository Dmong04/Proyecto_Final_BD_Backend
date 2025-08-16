package com.una.controllers;

import com.una.dto.SupplierDTO;
import com.una.dto.TourDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<TourDTO>> getAllTours() {
        try {
            return ResponseEntity.ok(service.getAllTours());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable Integer id) {
        try {
            Optional<TourDTO> found = service.getTourById(id);
            return found.map(tourDTO -> new ResponseEntity<>(tourDTO, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<TourDTO> createTour(@RequestBody TourDTO dto) {
        try {
            Optional<TourDTO> found = service.getTourByType(dto.getType());
            if (found.isPresent()) {
                ResponseEntity.badRequest().build();
                return null;
            }
            return ResponseEntity.ok(service.createTour(dto));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourDTO> updateTour(@PathVariable Integer id, @RequestBody TourDTO dto) {
        try {
            return service.getTourById(id)
                    .map(tour -> {
                        tour.setType(dto.getType());
                        tour.setDescription(dto.getDescription());
                        tour.setPrice(dto.getPrice());
                        return ResponseEntity.ok(service.createTour(tour));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourDTO> deleteTourById(@PathVariable Integer id) {
        try {
            Optional<TourDTO> found = service.getTourById(id);
            if (found.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteTourById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }

    }
}
