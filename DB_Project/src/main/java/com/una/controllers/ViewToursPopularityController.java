package com.una.controllers;

import com.una.models.VToursPopularity;
import com.una.services.VToursPopularityService;
import com.una.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("coco_tours/api/v2/views/tours_popularity")
public class ViewToursPopularityController {

    private final VToursPopularityService service;

    public ViewToursPopularityController(VToursPopularityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<VToursPopularity>>> getToursPopularity() {
        GenericResponse<List<VToursPopularity>> response = new GenericResponse<>();
        try {
            List<VToursPopularity> data = service.findAll();
            if (data.isEmpty()) {
                return response.buildResponse(null, false, "No hay registros de popularidad", HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(data, true, "Popularidad obtenida correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return response.buildResponse(null, false, "Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
