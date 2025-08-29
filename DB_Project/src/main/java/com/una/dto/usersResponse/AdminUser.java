package com.una.dto.usersResponse;

import com.una.dto.AdminDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {
    private Integer id;
    // private String name;
    private String email;
    private String username;
    private String role;
    private AdminDTO admin;
}
