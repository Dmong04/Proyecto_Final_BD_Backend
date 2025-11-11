package com.una.repositories;

import com.una.models.VReservationUpcoming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VReservationUpcomingRepository  extends JpaRepository<VReservationUpcoming,Integer> {

    @Query(value = "SELECT * FROM v_reservation_upcoming", nativeQuery = true)
    List<VReservationUpcoming> findAllFromView();
}