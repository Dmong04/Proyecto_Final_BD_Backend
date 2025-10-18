package com.una.services;

import com.una.dto.SupplierPhonesDTO;
import com.una.mappers.SupplierPhonesMapper;
import com.una.models.SupplierPhones;
import com.una.repositories.SupplierPhonesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierPhonesService {

    private final SupplierPhonesRepository supplierPhonesRepository;

    public  SupplierPhonesService(SupplierPhonesRepository supplierPhonesRepository) {
        this.supplierPhonesRepository = supplierPhonesRepository;
    }

    public List<SupplierPhonesDTO> findBySupplierName(String name) {
       return supplierPhonesRepository.findByClientName(name).stream()
               .map(SupplierPhonesMapper::toDTO).toList();
    }
}
