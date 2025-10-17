
package com.una.services;

import com.una.dto.ExtraDTO;
import com.una.mappers.ExtraMapper;
import com.una.models.Extra;
import com.una.repositories.ExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtraService {

    private final ExtraRepository extraRepository;

    public ExtraService(ExtraRepository extraRepository) {
        this.extraRepository = extraRepository;
    }

    public List<ExtraDTO> getAllExtras() {
        return extraRepository.findAll().stream()
                .map(ExtraMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ExtraDTO> findExtraById(Integer id) {
        var found = extraRepository.findById(id);
        return found.map(ExtraMapper::toDTO);
    }

    public Optional<ExtraDTO> findExtraByName(String name) {
        var found = extraRepository.findByName(name);
        return found.map(ExtraMapper::toDTO);
    }

    public void insertExtra(String name, String description, Integer price) {
        extraRepository.pa_extra_insert(name, description, price);
    }

    public void updateExtra(Integer id, String name, String description, Integer price) {
        extraRepository.pa_extra_update(id, name, description, price);
    }

    public void deleteExtraById(Integer id) {
        extraRepository.pa_extra_delete(id);
    }
}
