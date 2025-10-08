package com.una.controllers;

import com.una.dto.DetailExtraDTO;
import com.una.services.DetailExtraService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.detailExtra.AddDetailExtraRequest;
import com.una.utils.requests.detailExtra.UpdateDetailExtraRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/extra_details")
public class DetailExtraController {

    private final DetailExtraService service;

    public DetailExtraController(DetailExtraService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<DetailExtraDTO>>> getAllDetailExtras() {
        try {
            GenericResponse<List<DetailExtraDTO>> response = new GenericResponse<>();
            List<DetailExtraDTO> detailExtras = service.getAllExtraDetails();
            if (detailExtras.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de detalles extra está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(detailExtras, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<DetailExtraDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<DetailExtraDTO>> getDetailExtraById(@PathVariable Integer id) {
        try {
            Optional<DetailExtraDTO> detailExtra = service.findExtraDetailById(id);
            GenericResponse<DetailExtraDTO> response = new GenericResponse<>();
            if (detailExtra.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se ha encontrado el detalle extra con el ID seleccionado",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(detailExtra.get(), true,
                    "Se encontró el detalle extra con el ID seleccionado",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<DetailExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<GenericResponse<DetailExtraDTO>> createDetailExtra(@RequestBody AddDetailExtraRequest request) {
        try {
            Optional<DetailExtraDTO> found = service.findByExtraAndReservations(request.getExtra(), request.getReservations());
            GenericResponse<DetailExtraDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(found.get(), false,
                        "La creación del detalle extra no fue exitosa, ya existe un registro con el extra ID: " + found.get().getExtra() +
                                " y la reservación ID: " + found.get().getReservations(),
                        HttpStatus.BAD_REQUEST);
            }
            service.insertDetailExtra(request.getParticipants(), request.getExtra(), request.getReservations());
            return response.buildResponse(null, true,
                    "Creación del detalle extra exitosa",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<DetailExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<DetailExtraDTO>> updateDetailExtra(@PathVariable Integer id, @RequestBody UpdateDetailExtraRequest request) {
        try {
            Optional<DetailExtraDTO> found = service.findExtraDetailById(id);
            GenericResponse<DetailExtraDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se pudo actualizar el detalle extra, no se encontró el ID especificado",
                        HttpStatus.BAD_REQUEST);
            }

            service.updateDetailExtra(id, request.getParticipants(), request.getExtra());
            return response.buildResponse(null, true,
                    "La actualización del detalle extra ha sido exitosa",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<DetailExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<DetailExtraDTO>> deleteDetailExtraById(@PathVariable Integer id) {
        try {
            Optional<DetailExtraDTO> found = service.findExtraDetailById(id);
            GenericResponse<DetailExtraDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El detalle extra seleccionado no existe",
                        HttpStatus.BAD_REQUEST);
            }
            service.deleteExtraDetailById(id);
            return response.buildResponse(found.get(), true,
                    "Se eliminó el detalle extra exitosamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<DetailExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
