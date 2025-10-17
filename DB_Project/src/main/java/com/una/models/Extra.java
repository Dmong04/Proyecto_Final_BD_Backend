
package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "extra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private Integer unitPrice;
}
