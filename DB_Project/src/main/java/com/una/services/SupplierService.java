package com.una.services;

import com.una.dto.SupplierDTO;
import com.una.mappers.SupplierMapper;
import com.una.models.Supplier;
import com.una.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierDTO> getAllProviders() {
        return supplierRepository.findAll().stream().map(SupplierMapper::toDTO).toList();
    }

    public Optional<SupplierDTO> findProviderById(Integer id) {
        return supplierRepository.findById(id).map(SupplierMapper::toDTO);
    }

    public Optional<SupplierDTO> findProviderByName(String name) {
        return supplierRepository.findByName(name).map(SupplierMapper::toDTO);
    }

    public SupplierDTO createProvider(SupplierDTO dto) {
        Supplier supplier = SupplierMapper.toEntity(dto);
        supplier = supplierRepository.save(supplier);
        return SupplierMapper.toDTO(supplier);
    }

    public void deleteProviderById(Integer id) {
        supplierRepository.deleteById(id);
    }
}
