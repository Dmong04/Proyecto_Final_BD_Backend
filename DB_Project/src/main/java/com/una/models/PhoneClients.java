package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "telefonos_cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneClients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Client client;
    @Column(name = "telefono")
    private String phone;
}
