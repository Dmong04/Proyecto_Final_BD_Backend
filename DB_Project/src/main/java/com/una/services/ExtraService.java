package com.una.services;

import com.una.dto.ExtraDTO;
import com.una.mappers.ExtraMapper;
import com.una.models.Extra;
import com.una.repositories.ExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraService {

    private final ExtraRepository extraRepository;

    public ExtraService(ExtraRepository extraRepository) {
        this.extraRepository = extraRepository;
    }

    public List<ExtraDTO> getAllExtras() {
        return extraRepository.findAll().stream().map(ExtraMapper::toDTO).toList();
    }

    public Optional<ExtraDTO> findExtraById(Integer id) {
        return extraRepository.findById(id).map(ExtraMapper::toDTO);
    }

    public Optional<ExtraDTO> findExtraByName(String name) {
        return extraRepository.findExtraByName(name).map(ExtraMapper::toDTO);
    }

    public ExtraDTO createExtra(ExtraDTO dto) {
        Extra extra = ExtraMapper.toEntity(dto);
        extra = extraRepository.save(extra);
        return ExtraMapper.toDTO(extra);
    }

    public void deleteExtraById(Integer id) {
        extraRepository.deleteById(id);
    }
}
