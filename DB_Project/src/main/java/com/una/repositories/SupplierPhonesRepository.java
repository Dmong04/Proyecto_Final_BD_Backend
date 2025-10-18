package com.una.repositories;

import com.una.models.SupplierPhones;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierPhonesRepository {

    List<SupplierPhones> findByClientName(String name);
}
