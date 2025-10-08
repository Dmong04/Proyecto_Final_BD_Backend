package com.una.mappers;

import com.una.dto.ReservationDTO;
import com.una.models.DetailExtra;
import com.una.models.DetailTour;
import com.una.models.Reservation;
import com.una.models.User;

public interface ReservationMapper {
    public static ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setDate(reservation.getDate());
        dto.setTime(reservation.getTime());
        dto.setDescription(reservation.getDescription());
        dto.setTourPrice(reservation.getTourPrice());
        dto.setExtraPrice(reservation.getExtraPrice());
        dto.setTotal(reservation.getTotal());
        dto.setUser(UserMapper.toDTO(reservation.getUser()));
        return dto;
    }

    public static Reservation toEntity(ReservationDTO dto) {
        if (dto == null) return null;
        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setDate(dto.getDate());
        reservation.setTime(dto.getTime());
        reservation.setDescription(dto.getDescription());
        reservation.setUser(UserMapper.toEntity(dto.getUser()));
        return reservation;
    }
}
