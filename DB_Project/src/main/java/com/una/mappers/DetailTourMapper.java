package com.una.mappers;

import com.una.dto.DetailTourDTO;
import com.una.models.DetailTour;

public interface DetailTourMapper {

    public static DetailTourDTO toDTO(DetailTour detailTour) {
        if (detailTour == null) return null;
        DetailTourDTO dto = new DetailTourDTO();
        dto.setId(detailTour.getId());
        dto.setOrigin(detailTour.getOrigin());
        dto.setDestination(detailTour.getDestination());
        dto.setTour(detailTour.getTour() != null ? TourMapper.toDTO(detailTour.getTour()) : null);
        dto.setReservations(detailTour.getReservations() != null ? ReservationMapper.toDTO(detailTour.getReservations()) : null);
        dto.setProvider(detailTour.getSupplier() != null ? SupplierMapper.toDTO(detailTour.getSupplier()) : null);
        return dto;
    }

    public static DetailTour toEntity(DetailTourDTO dto) {
        DetailTour detailTour = new DetailTour();
        detailTour.setId(dto.getId());
        detailTour.setOrigin(dto.getOrigin());
        detailTour.setDestination(dto.getDestination());
        detailTour.setTour(dto.getTour() != null ? TourMapper.toEntity(dto.getTour()) : null);
        detailTour.setReservations(dto.getReservations() != null ? ReservationMapper.toEntity(dto.getReservations()) : null);
        detailTour.setSupplier(dto.getProvider() != null ? SupplierMapper.toEntity(dto.getProvider()) : null);

        return detailTour;
    }
}
