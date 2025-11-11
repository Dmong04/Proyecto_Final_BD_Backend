package com.una.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "v_reservation_upcoming")
@Data
public class VReservationUpcoming {
    @Id
    private Integer reserva_id;

    @Column(name = "cliente")
    private String client;

    @Column(name = "fecha")
    private java.sql.Date date;

    @Column(name = "total")
    private Double total;
}
