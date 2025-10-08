package com.una.services;

import com.una.dto.DetailTourDTO;
import com.una.mappers.DetailTourMapper;
import com.una.models.DetailTour;
import com.una.repositories.DetailTourRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DetailTourService {

    private final DetailTourRepository detailTourRepository;

    public DetailTourService(DetailTourRepository detailTourRepository) {
        this.detailTourRepository = detailTourRepository;
    }

    public List<DetailTourDTO> getAllDetails() {
        return detailTourRepository.findAll().stream().map(DetailTourMapper::toDTO).toList();
    }

    public Optional<DetailTourDTO> findDetailById(Integer id) {
        return detailTourRepository.findById(id).map(DetailTourMapper::toDTO);
    }

    public Optional<DetailTourDTO> findDetailByOrigin(String origin) {
        return detailTourRepository.findByOrigin(origin).map(DetailTourMapper::toDTO);
    }

    public void insertDetailTour(String origin, String destination, Integer tour_id, Integer reservation_id) {
        detailTourRepository.pa_tour_details_reservation_insert(origin, destination, tour_id, reservation_id);
    }

    public void updateDetailTour(Integer id, String origin, String destination, Integer tour_id, Integer reservation_id) {
        detailTourRepository.pa_tour_details_reservation_update(id, origin, destination, tour_id, reservation_id);
    }

    public void deleteDetailById(Integer id) {
        detailTourRepository.pa_tour_details_reservation_delete(id);
    }
}
