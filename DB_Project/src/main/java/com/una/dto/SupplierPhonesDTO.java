package com.una.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierPhonesDTO {
    public Integer id;
    public String phone;
    public SupplierDTO supplier;
}
