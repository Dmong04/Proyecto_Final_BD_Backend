package com.una.services;

import com.una.dto.TourDTO;
import com.una.mappers.TourMapper;
import com.una.models.Tour;
import com.una.repositories.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<TourDTO> getAllTours() {
        return tourRepository.findAll().stream().map(TourMapper::toDTO).toList();
    }

    public Optional<TourDTO> getTourById(Integer id) {
        return tourRepository.findById(id).map(TourMapper::toDTO);
    }

    public Optional<TourDTO> getTourByType(String type) {
        return tourRepository.findByType(type).map(TourMapper::toDTO);
    }

    public TourDTO createTour(TourDTO dto) {
        Tour tour = TourMapper.toEntity(dto);
        tour = tourRepository.save(tour);
        return TourMapper.toDTO(tour);
    }

    public void deleteTourById(Integer id) {
        tourRepository.deleteById(id);
    }
}
