package com.una.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private Integer user_id;
    private String name;
    private String username;
    private String role;
    private String token;
}
