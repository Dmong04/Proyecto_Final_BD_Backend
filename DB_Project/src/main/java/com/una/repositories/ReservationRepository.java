package com.una.repositories;

import com.una.models.Reservation;
import com.una.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM reservations WHERE CONVERT(VARCHAR, time, 108) = :timeStr AND date = :date", nativeQuery = true)
    Optional<Reservation> findByDateAndTime(@Param("date") LocalDate date, @Param("timeStr") String timeStr);

    @Procedure(name = "pa_reservation_insert")
    void pa_reservation_insert(@Param("date") LocalDate date, @Param("time") LocalTime time,
                            @Param("description") String description, @Param("user_id") Integer user_id);

    @Procedure(name = "pa_reservation_update")
    void pa_reservation_update(@Param("reservation_id") Integer reservation_id, @Param("date") LocalDate date,
                               @Param("time") LocalTime time,
                               @Param("description") String description, @Param("user_id") Integer user_id);

    @Procedure(name = "pa_reservation_delete")
    void pa_reservation_delete(@Param("reservation_id") Integer reservation_id);
}
