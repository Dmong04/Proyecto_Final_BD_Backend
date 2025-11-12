package com.una.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "v_tours_popularity")
@Data
public class VToursPopularity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // campo virtual para mapear correctamente

    @Column(name = "type")
    private String type;

    @Column(name = "veces_reservado")
    private Integer timesBooked;

    @Column(name = "ingresos_generados")
    private Double totalRevenue;
}
