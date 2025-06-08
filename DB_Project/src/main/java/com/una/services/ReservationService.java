package com.una.services;

import com.una.dto.ReservationDTO;
import com.una.mappers.ReservationMapper;
import com.una.models.Reservation;
import com.una.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDTO> getAllreservations() {
        return reservationRepository.findAll().stream().map(ReservationMapper::toDTO).toList();
    }

    public Optional<ReservationDTO> findReservationById(Integer id) {
        return reservationRepository.findById(id).map(ReservationMapper::toDTO);
    }

    public ReservationDTO createReservation(ReservationDTO dto) {
        Reservation reservation = ReservationMapper.toEntity(dto);
        reservation = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(reservation);
    }

    public void deleteReservationById(Integer id) {
        reservationRepository.deleteById(id);
    }
}
