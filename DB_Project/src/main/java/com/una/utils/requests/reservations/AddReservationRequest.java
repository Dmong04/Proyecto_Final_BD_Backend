package com.una.utils.requests.reservations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddReservationRequest {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private Integer user_id;
}
