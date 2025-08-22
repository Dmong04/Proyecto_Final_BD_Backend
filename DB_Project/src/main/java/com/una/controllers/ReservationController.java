package com.una.controllers;

import com.una.dto.ReservationDTO;
import com.una.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/reservation")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<ReservationDTO> getAllReservations() {
        return service.getAllreservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer id) {
        return service.findReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO dto) {
        return ResponseEntity.ok(service.createReservation(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Integer id, @RequestBody ReservationDTO dto) {
        return service.findReservationById(id)
                .map(reservation -> {
                    reservation.setDate(dto.getDate());
                    reservation.setTime(dto.getTime());
                    reservation.setDescription(dto.getDescription());
                    reservation.setDetailExtra(dto.getDetailExtra());
                    reservation.setDetailTour(dto.getDetailTour());
                    return ResponseEntity.ok(service.createReservation(reservation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> deleteReservationById(@PathVariable Integer id) {
        Optional<ReservationDTO> found = service.findReservationById(id);
        if (found.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        service.deleteReservationById(id);
        return ResponseEntity.ok().build();
    }
}
