package com.una.dto;

import com.una.models.User;
import lombok.Getter;

public class LoginResponseDTO {
    @Getter
    private String name;
    @Getter
    private String username;
    @Getter
    private String role;
    @Getter
    private String token;

    public LoginResponseDTO(String name, String username, String role, String token) {
        this.name = name;
        this.username = username;
        this.role = role;
        this.token = token;
    }
}
