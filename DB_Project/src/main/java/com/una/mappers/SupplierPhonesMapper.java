package com.una.mappers;

import com.una.dto.SupplierDTO;
import com.una.dto.SupplierPhonesDTO;
import com.una.models.SupplierPhones;

public interface SupplierPhonesMapper {

    static SupplierPhonesDTO toDTO(SupplierPhones supplierPhones) {
        if (supplierPhones == null)
            return null;
        SupplierPhonesDTO dto = new SupplierPhonesDTO();
        dto.setId(supplierPhones.getId());
        dto.setPhone(supplierPhones.getPhone());
        dto.setSupplier(SupplierMapper.toDTO(supplierPhones.getSupplier()));
        return dto;
    }

    static SupplierPhones toEntity(SupplierPhonesDTO dto) {
        if (dto == null)
            return null;
        SupplierPhones supplierPhones = new SupplierPhones();
        supplierPhones.setId(dto.getId());
        supplierPhones.setPhone(dto.getPhone());
        supplierPhones.setSupplier(SupplierMapper.toEntity(dto.getSupplier()));
        return supplierPhones;
    }
}
