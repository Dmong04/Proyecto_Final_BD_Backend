package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "detalle_viaje")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailTour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "origin")
    private String origin;
    @Column(name = "destination")
    private String destination;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @OneToMany(mappedBy = "details", cascade = CascadeType.ALL)
    private List<Passenger> passengers;
    @OneToMany(mappedBy = "detailTour")
    private List<Reservation> reservations;
}
