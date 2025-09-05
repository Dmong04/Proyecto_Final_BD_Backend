package com.una.controllers;

import org.springframework.web.bind.annotation.*;

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
//    public List<PassengerDTO> getAllPassengers() {
//        return service.getAllPassengers();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PassengerDTO> getPassengerById(@PathVariable Integer id) {
//        return service.getPassengerById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public PassengerDTO createPassenger(@RequestBody PassengerDTO dto) {
//        Optional<PassengerDTO> found = service.get(dto.getType());
//        if (found.isPresent()) {
//            ResponseEntity.badRequest().build();
//            return null;
//        }
//        return ResponseEntity.ok(service.createTour(dto)).getBody();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TourDTO> updateTour(@PathVariable Integer id, @RequestBody TourDTO dto) {
//        return service.getTourById(id)
//                .map(tour -> {
//                    tour.setType(dto.getType());
//                    tour.setDescription(dto.getDescription());
//                    tour.setPrice(dto.getPrice());
//                    return ResponseEntity.ok(service.createTour(tour));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<TourDTO> deleteTourById(@PathVariable Integer id) {
//        Optional<TourDTO> found = service.getTourById(id);
//        if (found.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        service.deleteTourById(id);
//        return ResponseEntity.ok().build();
//    }
}
