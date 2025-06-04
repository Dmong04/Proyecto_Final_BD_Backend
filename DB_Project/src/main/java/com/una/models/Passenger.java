package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pasajeros")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "edad")
    private Integer age;
    @ManyToOne
    @JoinColumn(name = "idDetalleViaje")
    private DetailTour details;
}
