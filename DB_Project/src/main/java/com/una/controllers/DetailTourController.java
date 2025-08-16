package com.una.controllers;

import com.una.dto.DetailExtraDTO;
import com.una.dto.DetailTourDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.DetailTourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/tour_details")
public class DetailTourController {

    private final DetailTourService service;

    public DetailTourController(DetailTourService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public  ResponseEntity<List<DetailTourDTO>> getAllDetails() {
        try {
            return ResponseEntity.ok(service.getAllDetails());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailTourDTO> getDetailById(@PathVariable Integer id) {
        try {
            Optional<DetailTourDTO> found = service.findDetailById(id);
            return found.map(detailExtraDTO -> new ResponseEntity<>(detailExtraDTO, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<DetailTourDTO> createDetail(@RequestBody DetailTourDTO dto) {
        try {
            Optional<DetailTourDTO> found = service.findDetailById(dto.getId());
            if (found.isPresent()) {
                ResponseEntity.badRequest().build();
                return null;
            }
//            ResponseEntity.ok();
            return ResponseEntity.ok(service.createDetail(dto));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailTourDTO> updateDetail(@PathVariable Integer id, @RequestBody DetailTourDTO dto) {
        try {
            return service.findDetailById(id)
                    .map(detail -> {
                        detail.setOrigin(dto.getOrigin());
                        detail.setDestination(dto.getDestination());
                        detail.setTour(dto.getTour());
                        detail.setProvider(dto.getProvider());
                        return ResponseEntity.ok(service.createDetail(detail));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetailTourDTO> deleteDetailById(@PathVariable Integer id) {
        try {
            Optional<DetailTourDTO> found = service.findDetailById(id);
            if (found.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteDetailById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
