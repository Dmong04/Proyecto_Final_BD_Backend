package com.una.controllers;

import com.una.dto.DetailTourDTO;
import com.una.dto.ExtraDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.ExtraService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ExtraDTO>> getAllExtras() {
        try {
            return ResponseEntity.ok(service.getAllExtras());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtraDTO> getExtraById(@PathVariable Integer id) {
        try {
            Optional<ExtraDTO> found = service.findExtraById(id);
            return found.map(extraDTO -> new ResponseEntity<>(extraDTO, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ExtraDTO> createExtra(@RequestBody ExtraDTO dto) {
        try {
            Optional<ExtraDTO> found = service.findExtraByName(dto.getName());
            if (found.isPresent()) {
                ResponseEntity.badRequest().build();
                return null;
            }
//            ResponseEntity.ok();
            return ResponseEntity.ok(service.createExtra(dto));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExtraDTO> updateExtra(@PathVariable Integer id, @RequestBody ExtraDTO dto) {
        try {
            return service.findExtraById(id)
                    .map(extra -> {
                        extra.setName(dto.getName());
                        extra.setDescription(dto.getDescription());
                        extra.setPrice(dto.getPrice());
                        return ResponseEntity.ok(service.createExtra(extra));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExtraDTO> deleteExtraById(@PathVariable Integer id) {
        try {
            Optional<ExtraDTO> found = service.findExtraById(id);
            if (found.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteExtraById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
