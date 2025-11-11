package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "v_month_income")
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class VMonthIncome {

    @Id
    @Column(name = "anio")
    private Integer anio;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ingresos_totales", precision = 10, scale = 2)
    private Double ingresosTotales;

    @Column(name = "cantidad_reservas")
    private Integer cantidadReservas;
}

