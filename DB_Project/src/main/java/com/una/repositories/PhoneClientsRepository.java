package com.una.repositories;

import com.una.models.ClientPhones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneClientsRepository extends JpaRepository<ClientPhones, Integer> {
}
