package com.una.services;

import com.una.models.VMonthIncome;
import com.una.models.VReservationUpcoming;
import com.una.models.VToursPopularity;
import com.una.repositories.VMonthIncomeRepository;
import com.una.repositories.VReservationUpcomingRepository;
import com.una.repositories.VToursPopularityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViewsService {

    private final VMonthIncomeRepository vMonthIncomeRepository;
    private final VReservationUpcomingRepository vReservationUpcomingRepository;
    private final VToursPopularityRepository vToursPopularityRepository;

    public ViewsService(VMonthIncomeRepository vMonthIncomeRepository,
                        VReservationUpcomingRepository vReservationUpcomingRepository,
                        VToursPopularityRepository vToursPopularityRepository) {
        this.vMonthIncomeRepository = vMonthIncomeRepository;
        this.vReservationUpcomingRepository = vReservationUpcomingRepository;
        this.vToursPopularityRepository = vToursPopularityRepository;
    }

    public List<VMonthIncome> getAllMonthIncome() {
        return vMonthIncomeRepository.findAllFromView();
    }

    public List<VReservationUpcoming> getAllUpcomingReservations() {
        return vReservationUpcomingRepository.findAllFromView();
    }

    public List<VToursPopularity> getAllToursPopularity() {
        return vToursPopularityRepository.findAllFromView();
    }
}
