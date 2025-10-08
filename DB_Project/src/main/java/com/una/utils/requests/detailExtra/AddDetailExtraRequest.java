package com.una.utils.requests.detailExtra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDetailExtraRequest {
    private int participants;
    private BigDecimal price;
    private Integer extra;
    private Integer reservations;
}
