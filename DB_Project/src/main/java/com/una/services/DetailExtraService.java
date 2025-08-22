package com.una.services;

import com.una.dto.DetailExtraDTO;
import com.una.dto.ExtraDTO;
import com.una.mappers.DetailExtraMapper;
import com.una.mappers.ExtraMapper;
import com.una.models.DetailExtra;
import com.una.models.Extra;
import com.una.repositories.DetailExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailExtraService {

    public final DetailExtraRepository detailExtraRepository;

    public DetailExtraService(DetailExtraRepository detailExtraRepository) {
        this.detailExtraRepository = detailExtraRepository;
    }

    public List<DetailExtraDTO> getAllExtraDetails() {
        return detailExtraRepository.findAll().stream().map(DetailExtraMapper::toDTO).toList();
    }

    public Optional<DetailExtraDTO> findExtraDetailById(Integer id) {
        return detailExtraRepository.findById(id).map(DetailExtraMapper::toDTO);
    }

    public DetailExtraDTO createExtraDetail(DetailExtraDTO dto) {
        DetailExtra detailExtra = DetailExtraMapper.toEntity(dto);
        detailExtra = detailExtraRepository.save(detailExtra);
        return DetailExtraMapper.toDTO(detailExtra);
    }

    public void deleteExtraDetailById(Integer id) {
        detailExtraRepository.deleteById(id);
    }
}
