package com.una.services;

import com.una.dto.UserDTO;
import com.una.mappers.UserMapper;
import com.una.models.User;
import com.una.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }

    public Optional<UserDTO> findUserByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::toDTO);
    }

    public Optional<UserDTO> findUserById(Integer id) {
        return userRepository.findById(id).map(UserMapper::toDTO);
    }

    public UserDTO createUser(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
