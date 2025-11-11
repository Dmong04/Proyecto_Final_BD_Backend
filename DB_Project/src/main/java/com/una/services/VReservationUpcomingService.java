package com.una.services;

import com.una.models.VReservationUpcoming;
import com.una.repositories.VReservationUpcomingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VReservationUpcomingService {

    private final VReservationUpcomingRepository repository;

    public VReservationUpcomingService(VReservationUpcomingRepository repository) {
        this.repository = repository;
    }

    public List<VReservationUpcoming> findAll(){
        return repository.findAll();
    }
}
