package com.una.controllers;

import com.una.dto.DetailTourDTO;
import com.una.dto.SupplierDTO;
import com.una.services.DetailTourService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.detailTour.AddDetailTourRequest;
import com.una.utils.requests.detailTour.UpdateDetailTourRequest;
import com.una.utils.requests.suppliers.AddSupplierRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/tour_details")
public class DetailTourController {

    private final DetailTourService service;

    public DetailTourController(DetailTourService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<DetailTourDTO>>> getAllDetailTours() {
        try {
            GenericResponse<List<DetailTourDTO>> response = new GenericResponse<>();
            List<DetailTourDTO> detailTours = service.getAllDetails();
            if (detailTours.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de detalles de tour está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(detailTours, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<DetailTourDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<DetailTourDTO>> getDetailTourById(@PathVariable Integer id) {
        try {
            Optional<DetailTourDTO> detailTour = service.findDetailById(id);
            GenericResponse<DetailTourDTO> response = new GenericResponse<>();
            if (detailTour.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se ha encontrado el detalle de tour con el ID seleccionado",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(detailTour.get(), true,
                    "Se encontró el detalle de tour con el ID seleccionado",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<DetailTourDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse<DetailTourDTO>> createSupplier(@RequestBody AddDetailTourRequest request) {
        try {
            Optional<DetailTourDTO> found = service.findDetailByOrigin(request.getOrigin());
            GenericResponse<DetailTourDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(found.get(), false,
                        "La creación del detalle de tour no fue exitosa, ya existe un detalle de tour",
                        HttpStatus.BAD_REQUEST);
            }
            service.insertDetailTour(request.getOrigin(), request.getDestination(), request.getTour(), request.getReservations());
            return response.buildResponse(null, true,
                    "Creación del detalle de tour exitosa",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<DetailTourDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<DetailTourDTO>> updateDetailTour(@PathVariable Integer id, @RequestBody UpdateDetailTourRequest request) {
        try {
            Optional<DetailTourDTO> found = service.findDetailById(id);
            GenericResponse<DetailTourDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se pudo actualizar el registro",
                        HttpStatus.BAD_REQUEST);
            }
            service.updateDetailTour(
                    id,
                    request.getOrigin(),
                    request.getDestination(),
                    request.getTour(),
                    request.getReservations()
            );
            return response.buildResponse(null, true,
                    "La actualización del registro ha sido exitosa",
                    HttpStatus.OK);

        } catch (Exception e) {
            GenericResponse<DetailTourDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<DetailTourDTO>> deleteDetailTourById(@PathVariable Integer id) {
        try {
            Optional<DetailTourDTO> found = service.findDetailById(id);
            GenericResponse<DetailTourDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El detalle de tour seleccionado no existe",
                        HttpStatus.BAD_REQUEST);
            }
            service.deleteDetailById(id);
            return response.buildResponse(found.get(), true,
                    "Se eliminó el registro exitosamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<DetailTourDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
