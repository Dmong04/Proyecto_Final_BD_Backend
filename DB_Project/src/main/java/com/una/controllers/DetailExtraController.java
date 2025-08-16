package com.una.controllers;

import com.una.dto.DetailExtraDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.DetailExtraService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/all")
    public ResponseEntity<List<DetailExtraDTO>> getAllExtraDetails() {
        try {
            return ResponseEntity.ok(service.getAllExtraDetails());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailExtraDTO> getExtraDetailById(@PathVariable Integer id) {
        try {
            Optional<DetailExtraDTO> found = service.findExtraDetailById(id);
            return found.map(detailExtraDTO -> new ResponseEntity<>(detailExtraDTO, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<DetailExtraDTO> createExtraDetail(@RequestBody DetailExtraDTO dto) {
        try {
            Optional<DetailExtraDTO> found = service.findExtraDetailById(dto.getId());
            if (found.isPresent()) {
                ResponseEntity.badRequest().build();
                return null;
            }
//            ResponseEntity.ok();
            return ResponseEntity.ok(service.createExtraDetail(dto));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailExtraDTO> updateExtraDetail(@PathVariable Integer id, @RequestBody DetailExtraDTO dto) {
        try {
            return service.findExtraDetailById(id)
                    .map(extraDetail -> {
                        extraDetail.setParticipants(dto.getParticipants());
                        extraDetail.setExtra(dto.getExtra());
                        return ResponseEntity.ok(service.createExtraDetail(extraDetail));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetailExtraDTO> deleteExtraDetailById(@PathVariable Integer id) {
        try {
            Optional<DetailExtraDTO> found = service.findExtraDetailById(id);
            if (found.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteExtraDetailById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
