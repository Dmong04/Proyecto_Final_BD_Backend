package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "correo")
    private String email;
    @Column(name = "usuario")
    private String username;
    @Column(name = "contraseña")
    private String password;
    @OneToOne
    @JoinColumn(name = "cliente", nullable = true)
    private Client client;
    @OneToOne
    @JoinColumn(name = "admin", nullable = true)
    private Admin admin;
}
