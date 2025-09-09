package com.una.utils.requests.clientUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddClientUserRequest {
    public String name;
    public String phone;
    public String email;
    public String username;
    public String password;
}
