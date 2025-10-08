package com.una.mappers;

import com.una.dto.DetailExtraDTO;
import com.una.models.DetailExtra;
import com.una.models.Extra;

public interface DetailExtraMapper {
    public static DetailExtraDTO toDTO(DetailExtra detailExtra) {
        if (detailExtra == null) return null;
        DetailExtraDTO dto = new DetailExtraDTO();
        dto.setId(detailExtra.getId());
        dto.setParticipants(detailExtra.getParticipants());
        dto.setPrice(detailExtra.getPrice());
        dto.setExtra(ExtraMapper.toDTO(detailExtra.getExtra()));
        dto.setReservations(detailExtra.getReservations() != null ? ReservationMapper.toDTO(detailExtra.getReservations()) : null);
        return dto;
    }

    public static DetailExtra toEntity(DetailExtraDTO dto) {
        if (dto == null) return null;
        DetailExtra detailExtra = new DetailExtra();
        detailExtra.setId(dto.getId());
        detailExtra.setParticipants(dto.getParticipants());
        detailExtra.setPrice(dto.getPrice());
        if (dto.getExtra() != null) {
            Extra extra = new Extra();
            extra.setId(dto.getExtra().getId());
            detailExtra.setExtra(extra);
        } else {
            detailExtra.setExtra(null);
        }
        detailExtra.setReservations(dto.getReservations() != null ? ReservationMapper.toEntity(dto.getReservations()) : null);
        return detailExtra;

    }
}
