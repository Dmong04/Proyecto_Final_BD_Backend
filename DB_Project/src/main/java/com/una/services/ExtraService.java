package com.una.services;

import com.una.dto.ExtraDTO;
import com.una.mappers.ExtraMapper;
import com.una.models.Extra;
import com.una.repositories.ExtraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        return extraRepository.findAll().stream().map(ExtraMapper::toDTO).toList();
    }

    public Optional<ExtraDTO> findExtraById(Integer id) {
        return extraRepository.findById(id).map(ExtraMapper::toDTO);
    }

    public Optional<ExtraDTO> findExtraByName(String name) {
        return extraRepository.findExtraByName(name).map(ExtraMapper::toDTO);
    }
    @Transactional
    public List<ExtraDTO> searchAllExtra() {
        List<Extra> extras = extraRepository.pa_extra_search_all();
        return extras.stream()
                .map(ExtraMapper::toDTO)
                .collect(Collectors.toList());
    }
//    @Transactional
//    public Optional<ExtraDTO> searchByIdExtra(Integer id ) {
//        Extra extra = extraRepository.pa_extra_search_by_id(id);
//        return Optional.ofNullable(extra).map(ExtraMapper::toDTO);
//    }

    public void insertExtra(String name, String description, BigDecimal price) {
        extraRepository.pa_extra_insert(name, description, price);
    }

    public void updateExtra(Integer id, String name, String description, BigDecimal price) {
        extraRepository.pa_extra_update(id, name, description, price);
    }

    public void deleteExtraById(Integer id) {
        extraRepository.pa_extra_delete(id);
    }
}
