package com.una.utils.requests.detailTour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDetailTourRequest {
    private String origin;
    private String destination;
    private Integer tour;
    private Integer reservations;
    private Integer provider;
}
