package com.una.utils.requests.adminUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdminUserRequest {
    public Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
}
