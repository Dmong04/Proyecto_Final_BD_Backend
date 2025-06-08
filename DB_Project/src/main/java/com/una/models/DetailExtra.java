package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "detalle_extra")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cantPersona")
    private int participants;
    @Column(name = "precioTotal", insertable = false, updatable = false)
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "idExtra")
    private Extra extra;
    @OneToMany(mappedBy = "detailExtra")
    private List<Reservation> reservations;
}
