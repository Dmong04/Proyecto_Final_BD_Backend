package com.una.services;

import com.una.models.VMonthIncome;
import com.una.repositories.VMonthIncomeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VMonthIncomeService {

    private  final VMonthIncomeRepository repository;

    public VMonthIncomeService(VMonthIncomeRepository repository) {
        this.repository = repository;
    }

    public List<VMonthIncome> findAll(){
        return repository.findAll();
    }
}
