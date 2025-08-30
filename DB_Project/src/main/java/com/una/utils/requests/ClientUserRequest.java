package com.una.utils.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientUserRequest {
    public String name;
    public String phone;
    public String email;
    public String username;
    public String password;
}
