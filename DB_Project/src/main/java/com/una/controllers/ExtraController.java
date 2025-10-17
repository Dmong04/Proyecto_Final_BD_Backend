
package com.una.controllers;

import com.una.dto.ExtraDTO;
import com.una.services.ExtraService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.extras.AddExtraRequest;
import com.una.utils.requests.extras.UpdateExtraRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/extra")
public class ExtraController {

    private final ExtraService service;

    public ExtraController(ExtraService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<ExtraDTO>>> getAllExtras() {
        GenericResponse<List<ExtraDTO>> response = new GenericResponse<>();
        try {
            List<ExtraDTO> extras = service.getAllExtras();
            if (extras == null || extras.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de extras está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(extras, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso: " + e.getClass().getSimpleName() + " - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ExtraDTO>> getExtraById(@PathVariable Integer id) {
        try {
            Optional<ExtraDTO> extra = service.findExtraById(id);
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            if (extra.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se ha encontrado el extra con el ID seleccionado",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(extra.get(), true,
                    "Se encontró el extra con el ID seleccionado",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GenericResponse<ExtraDTO>> getExtraByName(@PathVariable String name) {
        try {
            Optional<ExtraDTO> found = service.findExtraByName(name);
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El extra no existe",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(found.get(), true,
                    "Se encontró el extra en los registros",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse<ExtraDTO>> createSupplier(@RequestBody AddExtraRequest request) {
        try {
            Optional<ExtraDTO> found = service.findExtraByName(request.getName());
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(found.get(), false,
                        "La creación del extra no fue exitosa, ya existe un extra llamado: " + found.get().getName(),
                        HttpStatus.BAD_REQUEST);
            }
            service.insertExtra(request.getName(), request.getDescription(), request.getPrice());
            return response.buildResponse(null, true,
                    "Creación del extra exitosa",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<ExtraDTO>> updateExtra(@PathVariable Integer id, @RequestBody UpdateExtraRequest request) {
        try {
            Optional<ExtraDTO> found = service.findExtraById(id);
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se pudo actualizar el registro",
                        HttpStatus.BAD_REQUEST);
            }
            service.updateExtra(id, request.getName(), request.getDescription(), request.getPrice());
            return response.buildResponse(null, true,
                    "La actualización del registro ha sido exitosa",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<ExtraDTO>> deleteExtraById(@PathVariable Integer id) {
        try {
            Optional<ExtraDTO> found = service.findExtraById(id);
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El extra seleccionado no existe",
                        HttpStatus.BAD_REQUEST);
            }
            service.deleteExtraById(id);
            return response.buildResponse(found.get(), true,
                    "Se eliminó el registro exitosamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<ExtraDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
