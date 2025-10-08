package com.una.utils.requests.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSupplierRequest {
    private String name;
    private String description;
    private String email;
    private String phone;
}
