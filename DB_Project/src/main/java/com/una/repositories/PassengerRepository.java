package com.una.repositories;

import com.una.models.Passenger;
import com.una.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Optional<Passenger> findByName(String name);

    @Procedure(name = "pa_passenger_insert")
    void pa_passenger_insert(@Param("name") String name, @Param("age") Integer age,
                            @Param("tour_detail_id") Integer tour_detail_id);

    @Procedure(name = "pa_passenger_update")
    void pa_passenger_update(@Param("passenger_id") Integer passenger_id, @Param("name") String name, @Param("age") Integer age,
                             @Param("tour_detail_id") Integer tour_detail_id);

    @Procedure(name = "pa_passenger_delete")
    void pa_passenger_delete(@Param("passenger_id") Integer passenger_id);
}
