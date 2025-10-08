package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier_phones")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierPhones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "phone")
    private String phone;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
