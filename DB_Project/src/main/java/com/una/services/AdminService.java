package com.una.services;

import com.una.dto.AdminDTO;
import com.una.mappers.AdminMapper;
import com.una.models.Admin;
import com.una.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream().map(AdminMapper::toDTO).toList();
    }

    public Optional<AdminDTO> findAdminById(Integer id) {
        return adminRepository.findById(id).map(AdminMapper::toDTO);
    }

    public Optional<AdminDTO> findAdminByName(String name) {
        return adminRepository.findByName(name).map(AdminMapper::toDTO);
    }
}
