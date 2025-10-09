
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

    @Transactional(readOnly = true)
    public List<ExtraDTO> getAllExtras() {
        return extraRepository.findAll().stream()
                .map(ExtraMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ExtraDTO> findExtraById(Integer id) {
        System.out.println("=== Finding extra by ID: " + id + " ===");
        return extraRepository.findById(id).map(ExtraMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<ExtraDTO> findExtraByName(String name) {
        System.out.println("=== Finding extra by name: " + name + " ===");
        return extraRepository.findByName(name).map(ExtraMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<ExtraDTO> searchAllExtra() {
        System.out.println("=== ExtraService.searchAllExtra() called ===");
        try {
            List<Extra> extras = extraRepository.findAll();
            System.out.println("Found " + extras.size() + " extras in database");
            
            List<ExtraDTO> dtos = extras.stream()
                    .map(ExtraMapper::toDTO)
                    .collect(Collectors.toList());
            
            System.out.println("Converted to " + dtos.size() + " DTOs");
            return dtos;
        } catch (Exception e) {
            System.err.println("ERROR in searchAllExtra: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void insertExtra(String name, String description, BigDecimal price) {
        System.out.println("=== Inserting new extra: " + name + " ===");
        Extra extra = new Extra();
        extra.setName(name);
        extra.setDescription(description);
        extra.setUnitPrice(price);
        extraRepository.save(extra);
        System.out.println("Extra saved successfully");
    }

    @Transactional
    public void updateExtra(Integer id, String name, String description, BigDecimal price) {
        System.out.println("=== Updating extra with ID: " + id + " ===");
        Optional<Extra> found = extraRepository.findById(id);
        if (found.isPresent()) {
            Extra extra = found.get();
            extra.setName(name);
            extra.setDescription(description);
            extra.setUnitPrice(price);
            extraRepository.save(extra);
            System.out.println("Extra updated successfully");
        } else {
            System.out.println("Extra not found with ID: " + id);
        }
    }

    @Transactional
    public void deleteExtraById(Integer id) {
        System.out.println("=== Deleting extra with ID: " + id + " ===");
        extraRepository.deleteById(id);
        System.out.println("Extra deleted successfully");
    }
}
