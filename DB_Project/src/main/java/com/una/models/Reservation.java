package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalDate date;
    @Column(name = "hora")
    private LocalTime time;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "subtotalViaje", insertable = false, updatable = false)
    private Integer tourPrice;
    @Column(name = "subtotalExtra", insertable = false, updatable = false)
    private Integer extraPrice;
    @Column(name = "total", insertable = false, updatable = false)
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
