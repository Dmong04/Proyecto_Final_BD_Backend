package com.una.mappers;

import com.una.dto.AdminDTO;
import com.una.models.Admin;

public interface AdminMapper {

    public static AdminDTO toDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setName(admin.getName());
        return dto;
    }

    public static Admin toEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setName(dto.getName());
        return admin;
    }
}
