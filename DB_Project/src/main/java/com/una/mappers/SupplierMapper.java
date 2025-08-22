package com.una.mappers;

import com.una.dto.SupplierDTO;
import com.una.models.Supplier;

public interface SupplierMapper {

    public static SupplierDTO toDTO(Supplier supplier) {
        if (supplier == null) return null;
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setDescription(supplier.getDescription());
        dto.setEmail(supplier.getEmail());
        return dto;
    }

    public static Supplier toEntity(SupplierDTO dto) {
        if (dto == null) return null;
        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setDescription(dto.getDescription());
        supplier.setEmail(dto.getEmail());
        return supplier;
    }
}
