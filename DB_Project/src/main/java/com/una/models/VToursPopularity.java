package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "v_tours_popularity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VToursPopularity {

    @Id
    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "veces_reservado")
    private Integer vecesReservado;

    @Column(name = "ingresos_generados", precision = 10, scale = 2)
    private Double ingresosGenerados;
}