package com.una.controllers;

import com.una.dto.ReservationDTO;
import com.una.services.ReservationService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.reservations.AddReservationRequest;
import com.una.utils.requests.reservations.UpdateReservationRequest;
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
    public ResponseEntity<GenericResponse<List<ReservationDTO>>> getAllReservations() {
        try {
            GenericResponse<List<ReservationDTO>> response = new GenericResponse<>();
            List<ReservationDTO> reservations = service.getAllReservations();
            if (reservations.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de reservaciones está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(reservations, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<ReservationDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ReservationDTO>> getReservationById(@PathVariable Integer id) {
        try {
            Optional<ReservationDTO> reservation = service.findReservationById(id);
            GenericResponse<ReservationDTO> response = new GenericResponse<>();
            if (reservation.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se ha encontrado la reservación con el ID seleccionado",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(reservation.get(), true,
                    "Se encontró la reservación con el ID seleccionado",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<ReservationDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<GenericResponse<ReservationDTO>> createReservation(@RequestBody AddReservationRequest request) {
        try {
            GenericResponse<ReservationDTO> response = new GenericResponse<>();
            Optional<ReservationDTO> found = service.findReservationByDateTime(request.getDate(), request.getTime());
            if (found.isPresent()) {
                return response.buildResponse(found.get(), false,
                        "La creación de la reservacion no fue exitosa, " +
                                "ya existe un reservación para esta franja horaria",
                        HttpStatus.BAD_REQUEST);
            }
            service.insertReservation(request.getDate(), request.getTime(), request.getDescription(), request.getUser_id());
            return response.buildResponse(null, true,
                    "Creación de la reservacion exitosa",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<ReservationDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ReservationDTO>> updateReservation(@PathVariable Integer id, @RequestBody UpdateReservationRequest request) {
        try {
            Optional<ReservationDTO> found = service.findReservationById(id);
            GenericResponse<ReservationDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se pudo actualizar la reservación",
                        HttpStatus.BAD_REQUEST);
            }
            service.updateReservation(id, request.getDate(), request.getTime(), request.getDescription(), request.getUser_id());
            return response.buildResponse(null, true,
                    "La actualización de la reservación ha sido exitosa",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<ReservationDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<ReservationDTO>> deleteReservationById(@PathVariable Integer id) {
        try {
            Optional<ReservationDTO> found = service.findReservationById(id);
            GenericResponse<ReservationDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "La reservación seleccionada no existe",
                        HttpStatus.BAD_REQUEST);
            }
            service.deleteReservationById(id);
            return response.buildResponse(found.get(), true,
                    "Se eliminó la reservación exitosamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<ReservationDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
