package com.una.repositories;

import com.una.models.Extra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, Integer> {
    public Optional<Extra> findExtraByName(String name);
}
