package com.una.mappers;

import com.una.dto.ProviderDTO;
import com.una.models.Provider;

public interface ProviderMapper {

    public static ProviderDTO toDTO(Provider provider) {
        if (provider == null) return null;
        ProviderDTO dto = new ProviderDTO();
        dto.setId(provider.getId());
        dto.setName(provider.getName());
        dto.setDescription(provider.getDescription());
        dto.setEmail(provider.getEmail());
        return dto;
    }

    public static Provider toEntity(ProviderDTO dto) {
        if (dto == null || dto.getId() == null) return null;
        Provider provider = new Provider();
        provider.setId(dto.getId());
        provider.setName(dto.getName());
        provider.setDescription(dto.getDescription());
        provider.setEmail(dto.getEmail());
        return provider;
    }
}
