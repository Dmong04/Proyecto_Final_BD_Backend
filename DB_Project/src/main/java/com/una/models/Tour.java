package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "viaje")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tipo")
    private String type;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "precio")
    private Integer price;
    @OneToMany(mappedBy = "tour")
    private List<DetailTour> details;
}
