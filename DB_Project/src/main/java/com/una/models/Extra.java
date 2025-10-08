package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "extra")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_price")
    private BigDecimal price;
    @OneToMany(mappedBy = "extra")
    private List<DetailExtra> detailsExtra;
}
