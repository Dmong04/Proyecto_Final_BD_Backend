package com.una.controllers;

import com.una.models.VReservationUpcoming;
import com.una.services.VReservationUpcomingService;
import com.una.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("coco_tours/api/v2/views/reservations_upcoming")
public class ViewReservationUpcomingController {

    private final VReservationUpcomingService service;

    public ViewReservationUpcomingController(VReservationUpcomingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<VReservationUpcoming>>> getUpcomingReservations() {
        GenericResponse<List<VReservationUpcoming>> response = new GenericResponse<>();
        try {
            List<VReservationUpcoming> data = service.findAll();
            if (data.isEmpty()) {
                return response.buildResponse(null, false, "No hay reservas próximas", HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(data, true, "Reservas obtenidas correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return response.buildResponse(null, false, "Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}