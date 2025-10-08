package com.una.repositories;

import com.una.models.DetailExtra;
import com.una.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DetailExtraRepository extends JpaRepository<DetailExtra, Integer> {
    @Query("SELECT d FROM DetailExtra d WHERE d.extra.id = :extra_id AND d.reservations.id = :reservation_id")
    Optional<DetailExtra> findByExtraAndReservations(@Param("extra_id") Integer extra_id, @Param("reservation_id") Integer reservation_id);

//    Optional<DetailExtra> findByReservation(Integer id);

    @Procedure(name = "pa_extra_details_reservation_insert")
    void pa_extra_details_reservation_insert(@Param("person_count") Integer person_count, @Param("extra_id") Integer extra_id,
                            @Param("reservation_id") Integer reservation_id);

    @Procedure(name = "pa_extra_details_reservation_update")
    void pa_extra_details_reservation_update(@Param("extra_detail_id") Integer extra_detail_id, @Param("person_count") Integer person_count, @Param("extra_id") Integer extra_id);

    @Procedure(name = "pa_extra_details_reservation_delete")
    void pa_extra_details_reservation_delete(@Param("extra_detail_id") Integer extra_detail_id);
}
