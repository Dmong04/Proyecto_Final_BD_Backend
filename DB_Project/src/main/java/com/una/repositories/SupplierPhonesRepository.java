package com.una.repositories;

import com.una.models.SupplierPhones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierPhonesRepository extends JpaRepository<SupplierPhones, Integer> {

    List<SupplierPhones> findBySupplier_Name(String name);
}
