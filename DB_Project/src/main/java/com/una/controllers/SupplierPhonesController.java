package com.una.controllers;

import com.una.dto.SupplierPhonesDTO;
import com.una.services.SupplierPhonesService;
import com.una.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("coco_tours/api/v2/supplier_phones")
public class SupplierPhonesController {

    private final SupplierPhonesService service;

    public SupplierPhonesController(SupplierPhonesService service) {
        this.service = service;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GenericResponse<List<SupplierPhonesDTO>>> getBySupplierName(@PathVariable String name) {
        try {
            GenericResponse<List<SupplierPhonesDTO>> response = new GenericResponse<>();
            var found = service.findBySupplierName(name);
            if (found.isEmpty())
                return response.buildResponse(null, false,
                        "No se ha encontrado una lista de teléfonos para el proveedor",
                        HttpStatus.NO_CONTENT);
            return response.buildResponse(found, true,
                    "El listado por nombre de proveedor es exitoso",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<SupplierPhonesDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
