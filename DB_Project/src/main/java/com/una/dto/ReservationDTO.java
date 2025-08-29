package com.una.dto;

import com.una.dto.usersResponse.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private Integer tourPrice;
    private Integer extraPrice;
    private Integer total;
    private DetailExtraDTO detailExtra;
    private DetailTourDTO detailTour;
    private UserDTO user;
}
