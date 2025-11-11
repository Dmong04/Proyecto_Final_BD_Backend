package com.una.repositories;

import com.una.models.VToursPopularity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VToursPopularityRepository extends JpaRepository<VToursPopularity,Integer> {
    @Query(value = "SELECT * FROM v_tours_popularity", nativeQuery = true)
    List<VToursPopularity> findAllFromView();
}
