package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_phones")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientPhones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "phone")
    private String phone;
}
