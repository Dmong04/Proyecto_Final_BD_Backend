package com.una.controllers;

import com.una.models.VMonthIncome;
import com.una.services.VMonthIncomeService;
import com.una.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("coco_tours/api/v2/views/month_income")
public class ViewMonthIncomeController {

    private final VMonthIncomeService service;

    public ViewMonthIncomeController(VMonthIncomeService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<VMonthIncome>>> getMonthlyIncome() {
        GenericResponse<List<VMonthIncome>> response = new GenericResponse<>();
        try {
            List<VMonthIncome> data = service.findAll();
            if (data.isEmpty()) {
                return response.buildResponse(null, false, "No se encontraron registros", HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(data, true, "Datos cargados correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return response.buildResponse(null, false, "Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
