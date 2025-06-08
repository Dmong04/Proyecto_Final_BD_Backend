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
        dto.setDetailTour(DetailTourMapper.toDTO(passenger.getDetails()));
        return dto;
    }

    public static Passenger toEntity(PassengerDTO dto) {
        if (dto == null) return null;
        Passenger passenger = new Passenger();
        passenger.setId(dto.getId());
        passenger.setName(dto.getName());
        passenger.setAge(dto.getAge());
        passenger.setDetails(DetailTourMapper.toEntity(dto.getDetailTour()));
        return passenger;
    }
}
