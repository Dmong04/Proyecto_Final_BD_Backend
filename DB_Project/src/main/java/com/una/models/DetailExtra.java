package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "extra_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "person_count")
    private int participants;
    @Column(name = "total_price")
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "extra_id")
    private Extra extra;
    @OneToMany(mappedBy = "detailExtra")
    private List<Reservation> reservations;
}
