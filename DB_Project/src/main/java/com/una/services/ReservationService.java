package com.una.services;

import com.una.dto.ReservationDTO;
import com.una.mappers.ReservationMapper;
import com.una.models.Reservation;
import com.una.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream().map(ReservationMapper::toDTO).toList();
    }

    public Optional<ReservationDTO> findReservationById(Integer id) {
        return reservationRepository.findById(id).map(ReservationMapper::toDTO);
    }

    public Optional<ReservationDTO> findReservationByDateTime(LocalDate date, LocalTime time) {
        String timeStr = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return reservationRepository.findByDateAndTime(date, timeStr).map(ReservationMapper::toDTO);
    }

    @Transactional
    public void insertReservation(LocalDate date, LocalTime time, String description, Integer user_id) {
        reservationRepository.pa_reservation_insert(date, time, description, user_id);
    }

    @Transactional
    public void updateReservation(Integer id, LocalDate date, LocalTime time, String description, Integer user_id) {
        reservationRepository.pa_reservation_update(id, date, time, description, user_id);
    }

    @Transactional
    public void deleteReservationById(Integer id) {
        reservationRepository.pa_reservation_delete(id);
    }
}
