package com.una.controllers;

import com.una.models.VMonthIncome;
import com.una.models.VReservationUpcoming;
import com.una.models.VToursPopularity;
import com.una.services.ViewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vistas")
@CrossOrigin(origins = "*")
public class ViewsController {

    private final ViewsService viewsService;

    public ViewsController(ViewsService viewsService) {
        this.viewsService = viewsService;
    }

    @GetMapping("/ingresos-mensuales")
    public ResponseEntity<List<VMonthIncome>> getIngresosMensuales() {
        return ResponseEntity.ok(viewsService.getAllMonthIncome());
    }

    @GetMapping("/reservas-proximas")
    public ResponseEntity<List<VReservationUpcoming>> getReservasProximas() {
        return ResponseEntity.ok(viewsService.getAllUpcomingReservations());
    }

    @GetMapping("/tours-populares")
    public ResponseEntity<List<VToursPopularity>> getToursPopulares() {
        return ResponseEntity.ok(viewsService.getAllToursPopularity());
    }
}