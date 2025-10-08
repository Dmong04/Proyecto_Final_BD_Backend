package com.una.repositories;

import com.una.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    Optional<Tour> findByType(String type);

//    @Procedure(name = "pa_tour_search_all")
//    void pa_tour_search_all();
//
//    @Procedure(name = "pa_tour_search_by_id")
//    void pa_tour_search_by_id(@Param("tour_id") Integer id);

    @Procedure(name = "pa_tour_insert")
    void pa_tour_insert(@Param("type") String type, @Param("description") String description,
                        @Param("price") BigDecimal price);

    @Procedure(name = "pa_tour_update")
    void pa_tour_update(@Param("tour_id") Integer id, @Param("new_type") String newType, @Param("new_description") String newDescription, @Param("new_price") BigDecimal newPrice);

    @Procedure(name = "pa_tour_delete")
    void pa_tour_delete(@Param("tour_id") Integer id);
}
