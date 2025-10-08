package com.una.repositories;

import com.una.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Optional<Supplier> findByName(String name);

    @Procedure(name = "pa_supplier_insert")
    void pa_supplier_insert(@Param("name") String name, @Param("description") String description,
                            @Param("email") String email, @Param("phone") String phone);

    @Procedure(name = "pa_supplier_update")
    void pa_supplier_update(@Param("supplier_id") Integer supplier_id, @Param("name") String name, @Param("description") String description,
                            @Param("email") String email, @Param("phone") String phone);

    @Procedure(name = "pa_supplier_delete")
    void pa_supplier_delete(@Param("supplier_id") Integer supplier_id);
}
