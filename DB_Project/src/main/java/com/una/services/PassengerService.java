package com.una.services;
//
//import com.una.dto.DetailExtraDTO;
//import com.una.dto.PassengerDTO;
//import com.una.mappers.DetailExtraMapper;
//import com.una.mappers.PassengerMapper;
//import com.una.models.DetailExtra;
//import com.una.models.Passenger;
//import com.una.repositories.DetailExtraRepository;
//import com.una.repositories.PassengerRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
public class PassengerService {
//    public final PassengerRepository passengerRepository;
//
//    public PassengerService(PassengerRepository passengerRepository) {
//        this.passengerRepository = passengerRepository;
//    }
//
//    public List<PassengerDTO> getAllPassengers() {
//        return passengerRepository.findAll().stream().map(PassengerMapper::toDTO).toList();
//    }
//
//    public Optional<PassengerDTO> getPassengerById(Integer id) {
//        return passengerRepository.findById(id).map(PassengerMapper::toDTO);
//    }
//
//    public PassengerDTO createPassenger(PassengerDTO dto) {
//        Passenger passenger = PassengerMapper.toEntity(dto);
//        passenger = passengerRepository.save(passenger);
//        return PassengerMapper.toDTO(passenger);
//    }
//
//    public void deletePassengerById(Integer id) {
//        passengerRepository.deleteById(id);
//    }

}
