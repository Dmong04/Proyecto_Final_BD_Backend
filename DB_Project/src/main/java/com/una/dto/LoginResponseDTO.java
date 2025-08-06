package com.una.dto;

import com.una.models.User;
import lombok.Getter;

public class LoginResponseDTO {
    @Getter
    private String username;
    @Getter
    private String name;
    @Getter
    private String token;

    public LoginResponseDTO(String username, String name, String token) {
        this.username = username;
        this.name = name;
        this.token = token;
    }
}
