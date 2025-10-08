package com.una.utils.requests.extras;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddExtraRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
