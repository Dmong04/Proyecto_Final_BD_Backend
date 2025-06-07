package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "reservas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha")
    private Date date;
    @Column(name = "hora")
    private Time time;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "subtotalViaje")
    private Integer tourPrice;
    @Column(name = "subtotalExtra")
    private Integer extraPrice;
    @Column(name = "total")
    private Integer total;
    @ManyToOne
    @JoinColumn(name = "idDetalleExtra")
    private DetailExtra detailExtra;
    @ManyToOne
    @JoinColumn(name = "idDetalleViaje")
    private DetailTour detailTour;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private User user;
}
