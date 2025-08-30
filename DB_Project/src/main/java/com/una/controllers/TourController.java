package com.una.controllers;

import com.una.utils.GenericResponse;
import com.una.dto.TourDTO;
import com.una.services.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/tours")
public class TourController {

    private final TourService service;

    public TourController(TourService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<TourDTO>>> getAllTours() {
        try {
            GenericResponse<List<TourDTO>> response = new GenericResponse<>();
            List<TourDTO> tours = service.getAllTours();
            if (tours.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de tours está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(tours, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<TourDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TourDTO>> getTourById(@PathVariable Integer id) {
        try {
            Optional<TourDTO> tour = service.getTourById(id);
            GenericResponse<TourDTO> response = new GenericResponse<>();
            if (tour.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se ha encontrado el tour con el ID seleccionado",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(tour.get(), true,
                    "Se encontró el tour con el ID seleccionado",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<TourDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse<TourDTO>> createTour(@RequestBody TourDTO dto) {
        try {
            Optional<TourDTO> found = service.getTourByType(dto.getType());
            GenericResponse<TourDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(found.get(), false,
                        "La creación del tour no fue exitosa, ya existe un tour llamado: " + found.get().getType(),
                        HttpStatus.BAD_REQUEST);
            }
            service.insertTour(dto);
            return response.buildResponse(null, true,
                    "Creación del tour exitosa",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<TourDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TourDTO>> updateTour(@PathVariable Integer id, @RequestBody TourDTO dto) {
        try {
            Optional<TourDTO> found = service.getTourById(id);
            GenericResponse<TourDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se pudo actualizar el registro",
                        HttpStatus.BAD_REQUEST);
            }
            service.updateTour(dto);
            return response.buildResponse(null, true,
                    "La actualización del registro ha sido exitosa",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<TourDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<TourDTO>> deleteTourById(@PathVariable Integer id) {
        try {
            Optional<TourDTO> found = service.getTourById(id);
            GenericResponse<TourDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El registro no se encontró",
                        HttpStatus.NOT_FOUND);
            }
            service.deleteTourById(id);
            return response.buildResponse(found.get(), true,
                    "Se eliminó el registro exitosamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<TourDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
