package com.una.mappers;

import com.una.dto.SupplierDTO;
import com.una.models.Supplier;

import java.util.stream.Collectors;

public class SupplierMapper {
    public static SupplierDTO toDTO(Supplier supplier) {
        if (supplier == null)
            return null;
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setDescription(supplier.getDescription());
        dto.setEmail(supplier.getEmail());
        if (supplier.getPhones() != null) {
            dto.setPhones(supplier.getPhones().stream()
                    .map(SupplierPhonesMapper::toDTOWithoutSupplier)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static Supplier toEntity(SupplierDTO dto) {
        if (dto == null)
            return null;
        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setDescription(dto.getDescription());
        supplier.setEmail(dto.getEmail());
        // Note: Mapping phones back might not be needed depending on your use case,
        // as they are often managed separately.
        return supplier;
    }
}