package com.una.utils.requests.passengers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePassengersRequest {
    private String name;
    private Integer age;
    private Integer tour_detail;
}
