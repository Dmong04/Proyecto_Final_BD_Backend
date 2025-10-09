
package com.una.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer supplierId;
}
