package com.una.controllers;

import com.una.dto.ExtraDTO;
import com.una.dto.PassengerDTO;
import com.una.dto.TourDTO;
import com.una.exceptions.ServerErrorException;
import com.una.services.PassengerService;
import com.una.services.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/passengers")
public class PassengerController {
//    private final PassengerService service;
//
//    public PassengerController(PassengerService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
//        try {
//            return ResponseEntity.ok(service.getAllPassengers());
//        } catch (Exception e) {
//            throw new ServerErrorException(e.getMessage());
//        }
//
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PassengerDTO> getPassengerById(@PathVariable Integer id) {
//        try {
//            Optional<PassengerDTO> found = service.findPassengerById(id);
//            return found.map(passengerDTO -> new ResponseEntity<>(passengerDTO, HttpStatus.OK)).orElseGet(() ->
//                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        } catch (Exception e) {
//            throw new ServerErrorException(e.getMessage());
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody PassengerDTO dto) {
//        try {
//            Optional<PassengerDTO> found = service.getPassengerById(dto.getId());
//            if (found.isPresent()) {
//                ResponseEntity.badRequest().build();
//                return null;
//            }
//            return ResponseEntity.ok(service.createPassenger(dto)).getBody();
//        } catch (Exception e) {
//            throw new ServerErrorException(e.getMessage());
//        }
//
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable Integer id, @RequestBody PassengerDTO dto) {
//        try {
//            return service.getPassengerById(id)
//                    .map(passenger -> {
//                        passenger.setName(dto.getName());
//                        passenger.setAge(dto.getAge());
//                        passenger.setDetailTour(dto.getDetailTour());
//                        return ResponseEntity.ok(service.createPassenger(passenger));
//                    })
//                    .orElse(ResponseEntity.notFound().build());
//        } catch (Exception e) {
//            throw new ServerErrorException(e.getMessage());
//        }
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<PassengerDTO> deletePassengerById(@PathVariable Integer id) {
//        try {
//            Optional<PassengerDTO> found = service.getPassengerById(id);
//            if (found.isEmpty()) {
//                return ResponseEntity.notFound().build();
//            }
//            service.deletePassengerById(id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            throw new ServerErrorException(e.getMessage());
//        }
//
//    }
}
