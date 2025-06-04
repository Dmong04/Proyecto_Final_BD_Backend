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
    @Column(name = "numPasajeros")
    private Integer numPassengers;
    @Column(name = "origen")
    private String origin;
    @Column(name = "destino")
    private String destination;
    @ManyToOne
    @JoinColumn(name = "idViaje")
    private Tour tour;
    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private Provider provider;
    @OneToMany(mappedBy = "details", cascade = CascadeType.ALL)
    private List<Passenger> passengers;
}
