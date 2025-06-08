package com.una.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailExtraDTO {
    private Integer id;
    private int participants;
    private Integer price;
    private ExtraDTO extra;
}
