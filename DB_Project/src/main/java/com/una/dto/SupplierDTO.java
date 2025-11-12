package com.una.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private Integer id;
    private String name;
    private String description;
    private String email;
    private List<SupplierPhonesDTO> phones;
}