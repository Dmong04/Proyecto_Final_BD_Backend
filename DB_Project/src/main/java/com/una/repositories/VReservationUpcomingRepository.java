package com.una.repositories;
import com.una.models.VReservationUpcoming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VReservationUpcomingRepository extends JpaRepository<VReservationUpcoming, Integer> {
}
