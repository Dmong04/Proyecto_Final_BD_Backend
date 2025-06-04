package com.una.repositories;

import com.una.models.DetailTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailTourRepository extends JpaRepository<DetailTour, Integer> {
}
