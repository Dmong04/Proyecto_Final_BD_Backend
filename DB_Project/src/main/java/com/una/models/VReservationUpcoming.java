package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "v_reservation_upcoming")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VReservationUpcoming {
    @Id
    @Column(name = "reserva_id")
    private Integer reservaId;

    @Column(name = "cliente", length = 60)
    private String cliente;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "total", precision = 10, scale = 2)
    private Double total;
}