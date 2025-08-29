package com.una.repositories;

import com.una.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    Optional<Tour> findByType(String type);

    @Procedure(name = "pa_tour_insert")
    void pa_tour_insert(@Param("type") String type, @Param("description") String description,
                        @Param("price") Integer price);

    @Procedure(name = "pa_tour_update")
    void pa_tour_update(@Param("id") Integer id, String newType, String newDescription, Integer newPrice);

    @Procedure(name = "pa_tour_delete")
    void pa_tour_delete(@Param("id") Integer id);
}
