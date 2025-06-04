package com.una.services;

import com.una.dto.ProviderDTO;
import com.una.mappers.ProviderMapper;
import com.una.models.Provider;
import com.una.repositories.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public List<ProviderDTO> getAllProviders() {
        return providerRepository.findAll().stream().map(ProviderMapper::toDTO).toList();
    }

    public Optional<ProviderDTO> findProviderById(Integer id) {
        return providerRepository.findById(id).map(ProviderMapper::toDTO);
    }

    public Optional<ProviderDTO> findProviderByName(String name) {
        return providerRepository.findByName(name).map(ProviderMapper::toDTO);
    }

    public ProviderDTO createProvider(ProviderDTO dto) {
        Provider provider = ProviderMapper.toEntity(dto);
        provider = providerRepository.save(provider);
        return ProviderMapper.toDTO(provider);
    }

    public void deleteProviderById(Integer id) {
        providerRepository.deleteById(id);
    }
}
