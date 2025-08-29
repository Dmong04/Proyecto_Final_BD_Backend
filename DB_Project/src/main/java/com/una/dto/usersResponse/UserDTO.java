package com.una.dto.usersResponse;

import com.una.dto.AdminDTO;
import com.una.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String role;
    private String password;
    private ClientDTO client;
    private AdminDTO admin;
}
