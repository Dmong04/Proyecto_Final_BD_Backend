package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "[user]")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;
    @OneToOne
    @JoinColumn(name = "admin_id", nullable = true)
    private Admin admin;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;
}
