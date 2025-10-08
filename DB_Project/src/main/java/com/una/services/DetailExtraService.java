package com.una.services;

import com.una.dto.DetailExtraDTO;
import com.una.dto.ExtraDTO;
import com.una.mappers.DetailExtraMapper;
import com.una.mappers.ExtraMapper;
import com.una.models.DetailExtra;
import com.una.models.Extra;
import com.una.repositories.DetailExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailExtraService {

    public final DetailExtraRepository detailExtraRepository;

    public DetailExtraService(DetailExtraRepository detailExtraRepository) {
        this.detailExtraRepository = detailExtraRepository;
    }

    public List<DetailExtraDTO> getAllExtraDetails() {
        return detailExtraRepository.findAll().stream().map(DetailExtraMapper::toDTO).toList();
    }

    public Optional<DetailExtraDTO> findExtraDetailById(Integer id) {
        return detailExtraRepository.findById(id).map(DetailExtraMapper::toDTO);
    }

    public Optional<DetailExtraDTO> findByExtraAndReservations(Integer extra_id, Integer reservation_id) {
        return detailExtraRepository.findByExtraAndReservations(extra_id, reservation_id).map(DetailExtraMapper::toDTO);
    }

//    public Optional<DetailExtraDTO> findExtraDetailByReservation(Integer id) {
//        return detailExtraRepository.findByReservation(id).map(DetailExtraMapper::toDTO);
//    }

    public void insertDetailExtra(Integer person_count, Integer extra_id, Integer reservation_id) {
        detailExtraRepository.pa_extra_details_reservation_insert(person_count, extra_id, reservation_id);
    }

    public void updateDetailExtra(Integer id, Integer person_count, Integer extra_id) {
        detailExtraRepository.pa_extra_details_reservation_update(id, person_count, extra_id);
    }

    public void deleteExtraDetailById(Integer id) {
        detailExtraRepository.pa_extra_details_reservation_delete(id);
    }
}
