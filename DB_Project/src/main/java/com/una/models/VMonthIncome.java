package com.una.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "v_month_income")
@Data
public class VMonthIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "anio")
    private Integer year;

    @Column(name = "mes")
    private Integer month;

    @Column(name = "ingresos_totales")
    private Double totalIncome;

    @Column(name = "cantidad_reservas")
    private Integer totalReservations;
}
