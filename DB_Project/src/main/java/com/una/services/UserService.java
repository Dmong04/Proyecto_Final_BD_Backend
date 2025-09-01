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

    public UserService(UserRepository userRepository) {
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

    // Métodos de admin
    public void saveAdminUser(String name, String email, String username, String password) {
        userRepository.pa_admin_insert(name, email, username, password);
    }

    public void updateAdminUser(Integer id, String name, String email, String username, String password) {
        userRepository.pa_admin_update(id, name, email, username, password);
    }

    public void deleteAdminUser(Integer id) {
        userRepository.pa_admin_delete(id);
    }
    // Métodos de admin

    public void saveClientUser(String name, String phone, String email, String username, String password) {
        userRepository.pa_client_insert(name, phone, email, username, password);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
