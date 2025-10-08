package com.una.services;
import com.una.dto.PassengerDTO;
import com.una.mappers.PassengerMapper;
import com.una.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    public final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<PassengerDTO> getAllPassengers() {
        return passengerRepository.findAll().stream().map(PassengerMapper::toDTO).toList();
    }

    public Optional<PassengerDTO> getPassengerById(Integer id) {
        return passengerRepository.findById(id).map(PassengerMapper::toDTO);
    }

    public Optional<PassengerDTO> getPassengerByName(String name) {
        return passengerRepository.findByName(name).map(PassengerMapper::toDTO);
    }

    public void insertPassenger(String name, Integer age, Integer tour_detail_id) {
        passengerRepository.pa_passenger_insert(name, age, tour_detail_id);
    }

    public void updatePassenger(Integer id, String name, Integer age, Integer tour_detail_id) {
        passengerRepository.pa_passenger_update(id, name, age, tour_detail_id);
    }

    public void deletePassengerById(Integer id) {
        passengerRepository.pa_passenger_delete(id);
    }
}

