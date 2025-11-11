package com.una.repositories;

import com.una.models.VMonthIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VMonthIncomeRepository  extends JpaRepository<VMonthIncome, Integer>{

    @Query(value = "SELECT * FROM v_month_income", nativeQuery = true)
    List<VMonthIncome> findAllFromView();
}
