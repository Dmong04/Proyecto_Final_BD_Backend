package com.una.repositories;

import com.una.models.PhoneClients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneClientsRepository extends JpaRepository<PhoneClients, Integer> {
}
