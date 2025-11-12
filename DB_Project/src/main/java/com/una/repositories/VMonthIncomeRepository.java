package com.una.repositories;

import com.una.models.VMonthIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VMonthIncomeRepository  extends JpaRepository<VMonthIncome, Integer>{
}
