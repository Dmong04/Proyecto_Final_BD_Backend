package com.una.repositories;
import com.una.models.VToursPopularity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VToursPopularityRepository extends JpaRepository<VToursPopularity,Integer> {
}
