package com.una.utils.requests.tours;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTourRequest {
    private String type;
    private String description;
    private Integer price;
}
