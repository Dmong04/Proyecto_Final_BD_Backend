package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;
    @Column(name = "description")
    private String description;
    @Column(name = "tour_subtotal", insertable = false, updatable = false)
    private Integer tourPrice;
    @Column(name = "extra_subtotal", insertable = false, updatable = false)
    private Integer extraPrice;
    @Column(name = "total", insertable = false, updatable = false)
    private Integer total;
    @ManyToOne
    @JoinColumn(name = "extra_detail_id")
    private DetailExtra detailExtra;
    @ManyToOne
    @JoinColumn(name = "tour_detail_id")
    private DetailTour detailTour;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
