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

    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream().map(SupplierMapper::toDTO).toList();
    }

    public Optional<SupplierDTO> findSupplierById(Integer id) {
        return supplierRepository.findById(id).map(SupplierMapper::toDTO);
    }

    public Optional<SupplierDTO> findSupplierByName(String name) {
        return supplierRepository.findByName(name).map(SupplierMapper::toDTO);
    }

    public void insertSupplier(String name, String description, String email, String phone) {
        supplierRepository.pa_supplier_insert(name, description, email, phone);
    }

    public void updateSupplier(Integer id, String name, String description, String email, String phone) {
        supplierRepository.pa_supplier_update(id, name, description, email, phone);
    }

    public void deleteSupplierById(Integer id) {
        supplierRepository.pa_supplier_delete(id);
    }
}
