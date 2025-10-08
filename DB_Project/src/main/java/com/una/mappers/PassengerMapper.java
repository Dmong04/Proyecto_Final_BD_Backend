package com.una.mappers;

import com.una.dto.PassengerDTO;
import com.una.models.Passenger;

public interface PassengerMapper {
    public static PassengerDTO toDTO(Passenger passenger) {
        if (passenger == null) return null;
        PassengerDTO dto = new PassengerDTO();
        dto.setId(passenger.getId());
        dto.setName(passenger.getName());
        dto.setAge(passenger.getAge());
        dto.setTour_detail(DetailTourMapper.toDTO(passenger.getTour_detail()));
        return dto;
    }

    public static Passenger toEntity(PassengerDTO dto) {
        if (dto == null) return null;
        Passenger passenger = new Passenger();
        passenger.setId(dto.getId());
        passenger.setName(dto.getName());
        passenger.setAge(dto.getAge());
        passenger.setTour_detail(DetailTourMapper.toEntity(dto.getTour_detail()));
        return passenger;
    }
}
