package com.una.utils.requests.clientUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientUserRequest {
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
}
