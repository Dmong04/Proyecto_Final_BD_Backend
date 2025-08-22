package com.una.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailTourDTO {
    private Integer id;
    private String origin;
    private String destination;
    private TourDTO tour;
    private SupplierDTO provider;
}
