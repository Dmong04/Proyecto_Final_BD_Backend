package com.una.controllers;

import com.una.dto.SupplierDTO;
import com.una.services.SupplierService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.suppliers.AddSupplierRequest;
import com.una.utils.requests.suppliers.UpdateSupplierRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/supplier")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<SupplierDTO>>> getAllSuppliers() {
        try {
            GenericResponse<List<SupplierDTO>> response = new GenericResponse<>();
            List<SupplierDTO> suppliers = service.getAllSuppliers();
            if (suppliers.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de proveedores está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(suppliers, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<SupplierDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<SupplierDTO>> getSupplierById(@PathVariable Integer id) {
        try {
            Optional<SupplierDTO> supplier = service.findSupplierById(id);
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            if (supplier.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se ha encontrado el proveedor con el ID seleccionado",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(supplier.get(), true,
                    "Se encontró el proveedor con el ID seleccionado",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GenericResponse<SupplierDTO>> getSupplierByName(@PathVariable String name) {
        try {
            Optional<SupplierDTO> found = service.findSupplierByName(name);
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El proveedor no existe",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(found.get(), true,
                    "Se encontró al proveedor en los registros",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse<SupplierDTO>> createSupplier(@RequestBody AddSupplierRequest request) {
        try {
            Optional<SupplierDTO> found = service.findSupplierByName(request.getName());
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(found.get(), false,
                        "La creación del proveedor no fue exitosa, ya existe un proveedor llamado: " + found.get().getName() + " con el email: " + found.get().getEmail(),
                        HttpStatus.BAD_REQUEST);
            }
            service.insertSupplier(request.getName(), request.getDescription(), request.getEmail(), request.getPhone());
            return response.buildResponse(null, true,
                    "Creación del proveedor exitosa",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<SupplierDTO>> updateSupplier(@PathVariable Integer id, @RequestBody UpdateSupplierRequest request) {
        try {
            Optional<SupplierDTO> found = service.findSupplierById(id);
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se pudo actualizar el registro",
                        HttpStatus.BAD_REQUEST);
            }
            service.updateSupplier(id, request.getName(), request.getDescription(), request.getEmail(), request.getPhone());
            return response.buildResponse(null, true,
                    "La actualización del registro ha sido exitosa",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<SupplierDTO>> deleteSupplierById(@PathVariable Integer id) {
        try {
            Optional<SupplierDTO> found = service.findSupplierById(id);
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El proveedor seleccionado no existe",
                        HttpStatus.BAD_REQUEST);
            }
            service.deleteSupplierById(id);
            return response.buildResponse(found.get(), true,
                    "Se eliminó el registro exitosamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<SupplierDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
