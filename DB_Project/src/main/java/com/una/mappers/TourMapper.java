package com.una.mappers;

import com.una.dto.TourDTO;
import com.una.models.Tour;

public interface TourMapper {

    public static TourDTO toDTO(Tour tour) {
        TourDTO dto = new TourDTO();
        dto.setId(tour.getId());
        dto.setType(tour.getType());
        dto.setDescription(tour.getDescription());
        dto.setPrice(tour.getPrice());
        return dto;
    }

    public static Tour toEntity(TourDTO dto) {
        Tour tour = new Tour();
        tour.setId(dto.getId());
        tour.setType(dto.getType());
        tour.setDescription(dto.getDescription());
        tour.setPrice(dto.getPrice());
        return tour;
    }
}
