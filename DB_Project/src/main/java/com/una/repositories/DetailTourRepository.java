package com.una.repositories;

import com.una.models.DetailTour;
import com.una.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface DetailTourRepository extends JpaRepository<DetailTour, Integer> {
    Optional<DetailTour> findByOrigin(String origin);

    @Procedure(name = "pa_tour_details_reservation_insert")
    void pa_tour_details_reservation_insert(@Param("origin") String origin, @Param("@destination") String destination,
                               @Param("tour_id") Integer tour_id, @Param("reservation_id") Integer reservation_id);

    @Procedure(name = "pa_tour_details_reservation_update")
    void pa_tour_details_reservation_update(@Param("tour_detail_id") Integer tour_detail_id, @Param("origin") String origin, @Param("@destination") String destination,
                                            @Param("tour_id") Integer tour_id, @Param("reservation_id") Integer reservation_id);

    @Procedure(name = "pa_tour_details_reservation_delete")
    void pa_tour_details_reservation_delete(@Param("tour_detail_id") Integer tour_detail_id);
}
