package com.una.controllers;

import com.una.dto.ExtraDTO;
import com.una.dto.ReservationDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.ReservationService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        try {
            return ResponseEntity.ok(service.getAllreservations());
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer id) {
        try {
            Optional<ReservationDTO> found = service.findReservationById(id);
            return found.map(reservationDTO -> new ResponseEntity<>(reservationDTO, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO dto) {
        try {
            Optional<ReservationDTO> found = service.findReservationById(dto.getId());
            if (found.isPresent()) {
                ResponseEntity.badRequest().build();
                return null;
            }
//            ResponseEntity.ok();
            return ResponseEntity.ok(service.createReservation(dto));
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Integer id, @RequestBody ReservationDTO dto) {
        try {
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
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> deleteReservationById(@PathVariable Integer id) {
        try {
            Optional<ReservationDTO> found = service.findReservationById(id);
            if (found.isEmpty()) {
                ResponseEntity.notFound().build();
            }
            service.deleteReservationById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
