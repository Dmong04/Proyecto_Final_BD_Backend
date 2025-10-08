package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tour_detail")
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
    @JoinColumn(name = "reservation_id")
    private Reservation reservations;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @OneToMany(mappedBy = "tour_detail", cascade = CascadeType.ALL)
    private List<Passenger> passengers;
}
