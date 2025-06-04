package com.una.mappers;

import com.una.dto.DetailTourDTO;
import com.una.models.DetailTour;

public interface DetailTourMapper {

    public static DetailTourDTO toDTO(DetailTour detailTour) {
        DetailTourDTO dto = new DetailTourDTO();
        dto.setId(detailTour.getId());
        dto.setNumPassengers(detailTour.getNumPassengers());
        dto.setOrigin(detailTour.getOrigin());
        dto.setDestination(detailTour.getDestination());
        dto.setTour(TourMapper.toDTO(detailTour.getTour()));
        dto.setProvider(ProviderMapper.toDTO(detailTour.getProvider()));
        return dto;
    }

    public static DetailTour toEntity(DetailTourDTO dto) {
        DetailTour detailTour = new DetailTour();
        detailTour.setId(dto.getId());
        detailTour.setNumPassengers(dto.getNumPassengers());
        detailTour.setOrigin(dto.getOrigin());
        detailTour.setDestination(dto.getDestination());
        detailTour.setTour(TourMapper.toEntity(dto.getTour()));
        detailTour.setProvider(ProviderMapper.toEntity(dto.getProvider()));
        return detailTour;
    }
}
