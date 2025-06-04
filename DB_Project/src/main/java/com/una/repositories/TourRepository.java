package com.una.repositories;

import com.una.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    public Optional<Tour> findByType(String type);
}
