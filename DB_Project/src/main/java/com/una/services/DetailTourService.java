package com.una.services;

import com.una.dto.DetailTourDTO;
import com.una.mappers.DetailTourMapper;
import com.una.models.DetailTour;
import com.una.repositories.DetailTourRepository;
import org.springframework.stereotype.Service;

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

    // Opción crear métodos para filtrar por playa de origen y destino

    public DetailTourDTO createDetail(DetailTourDTO dto) {
        DetailTour detail = DetailTourMapper.toEntity(dto);
        detail = detailTourRepository.save(detail);
        return DetailTourMapper.toDTO(detail);
    }

    public void deleteDetailById(Integer id) {
        detailTourRepository.deleteById(id);
    }
}
