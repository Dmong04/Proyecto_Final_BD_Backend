package com.una.mappers;

import com.una.dto.usersResponse.UserDTO;
import com.una.models.User;

public interface UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        // dto.setPassword(user.getPassword());
        dto.setClient(user.getClient() != null ? ClientMapper.toDTO(user.getClient()) : null);
        dto.setAdmin(user.getAdmin() != null ? AdminMapper.toDTO(user.getAdmin()) : null);
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        // user.setPassword(dto.getPassword());
        user.setClient(dto.getClient() != null ? ClientMapper.toEntity(dto.getClient()) : null);
        user.setAdmin(dto.getAdmin() != null ? AdminMapper.toEntity(dto.getAdmin()) : null);
        return user;
    }
}
