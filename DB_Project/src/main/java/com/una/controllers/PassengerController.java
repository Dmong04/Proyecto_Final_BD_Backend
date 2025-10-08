package com.una.controllers;

import com.una.dto.PassengerDTO;
import com.una.services.PassengerService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.passengers.AddPassengerRequest;
import com.una.utils.requests.passengers.UpdatePassengersRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/passengers")
public class PassengerController {
    private final PassengerService service;

    public PassengerController(PassengerService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<PassengerDTO>>> getAllPassengers() {
        try {
            GenericResponse<List<PassengerDTO>> response = new GenericResponse<>();
            List<PassengerDTO> passengers = service.getAllPassengers();
            if (passengers.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de pasajeros está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(passengers, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<PassengerDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<PassengerDTO>> getPassengerById(@PathVariable Integer id) {
        try {
            Optional<PassengerDTO> passenger = service.getPassengerById(id);
            GenericResponse<PassengerDTO> response = new GenericResponse<>();
            if (passenger.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se ha encontrado el pasajero con el ID seleccionado",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(passenger.get(), true,
                    "Se encontró el pasajero con el ID seleccionado",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<PassengerDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse<PassengerDTO>> createPassenger(@RequestBody AddPassengerRequest request) {
        try {
            Optional<PassengerDTO> found = service.getPassengerByName(request.getName());
            GenericResponse<PassengerDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(found.get(), false,
                        "La creación del pasajero no fue exitosa, ya existe un pasajero llamado: " + found.get().getName(),
                        HttpStatus.BAD_REQUEST);
            }
            service.insertPassenger(request.getName(), request.getAge(), request.getTour_detail());
            return response.buildResponse(null, true,
                    "Creación del pasajero exitosa",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<PassengerDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<PassengerDTO>> updatePassenger(@PathVariable Integer id, @RequestBody UpdatePassengersRequest request) {
        try {
            Optional<PassengerDTO> found = service.getPassengerById(id);
            GenericResponse<PassengerDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se pudo actualizar el registro",
                        HttpStatus.BAD_REQUEST);
            }
            service.updatePassenger(id, request.getName(), request.getAge(), request.getTour_detail());
            return response.buildResponse(null, true,
                    "La actualización del registro ha sido exitosa",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<PassengerDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<PassengerDTO>> deletePassengerById(@PathVariable Integer id) {
        try {
            Optional<PassengerDTO> found = service.getPassengerById(id);
            GenericResponse<PassengerDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El pasajero seleccionado no existe",
                        HttpStatus.BAD_REQUEST);
            }
            service.deletePassengerById(id);
            return response.buildResponse(found.get(), true,
                    "Se eliminó el registro exitosamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<PassengerDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
