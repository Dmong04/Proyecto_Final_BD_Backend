package com.una.controllers;

import com.una.utils.GenericResponse;
import com.una.dto.TourDTO;
import com.una.services.TourService;
import com.una.utils.requests.tours.AddTourRequest;
import com.una.utils.requests.tours.UpdateTourRequest;
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
/**
 * Obtiene todos los tours registrados en el sistema.
 * @return Lista de tours envuelta en {@link GenericResponse}.
 * Retorna 204 si no hay tours*/
    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<TourDTO>>> getAllTours() {
        try {
            System.out.println("=== GET ALL TOURS REQUEST ===");
            System.out.println("Endpoint hit: /coco_tours/api/v2/tours/all");
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

    /**
     * Obtiene un tour por medio de su identificador.
     * @return Información completa del tour envuelta en {@link GenericResponse}.
     * Retorna 404 en caso de no existir el tour*/
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

    /**
     * Crea un tour mediante un procedimiento almacenado en la base de datos.
     * con la información solicitadda en {@link AddTourRequest}.
     * @return Mensaje de confirmación en la creación del tour envuelto en {@link GenericResponse}.
     * Retorna un 404 en caso de fallar la transacción.*/
    @PostMapping
    public ResponseEntity<GenericResponse<TourDTO>> createTour(@RequestBody AddTourRequest request) {
        try {
            Optional<TourDTO> found = service.getTourByType(request.getType());
            GenericResponse<TourDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(found.get(), false,
                        "La creación del tour no fue exitosa, ya existe un tour llamado: " + found.get().getType(),
                        HttpStatus.BAD_REQUEST);
            }
            service.insertTour(request.getType(), request.getDescription(), request.getPrice());
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

    /**
     * Actualiza la información de un tour seleccionado mediante un procedimiento almacenado
     * en la base de datos, al enviarse los datos en una {@link UpdateTourRequest}.
     * @return Mensaje de confirmación en la actualización del tour seleccionado envuelto en {@link GenericResponse}.
     * Retorna un 404 en caso de fallar la transacción.*/
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TourDTO>> updateTour(@PathVariable Integer id, @RequestBody UpdateTourRequest request) {
        try {
            Optional<TourDTO> found = service.getTourById(id);
            GenericResponse<TourDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se pudo actualizar el registro",
                        HttpStatus.BAD_REQUEST);
            }
            service.updateTour(id, request.getType(), request.getDescription(), request.getPrice());
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

    /**
     * Elimina el tour seleccionado mediante su identificador {@link Integer id}
     * @return Mensaje de confirmación en la eliminación del tour seleccionado envuelto en {@link GenericResponse}.
     * Retorna un 400 en caso de fallar la transación*/
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<TourDTO>> deleteTourById(@PathVariable Integer id) {
        try {
            Optional<TourDTO> found = service.getTourById(id);
            GenericResponse<TourDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El tour seleccionado no existe",
                        HttpStatus.BAD_REQUEST);
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
