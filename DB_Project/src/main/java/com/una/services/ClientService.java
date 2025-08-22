package com.una.services;

import com.una.dto.ClientDTO;
import com.una.mappers.ClientMapper;
import com.una.models.Client;
import com.una.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(ClientMapper::toDTO).toList();
    }

    public Optional<ClientDTO> getClientById(Integer id) {
        return clientRepository.findById(id).map(ClientMapper::toDTO);
    }

    public Optional<ClientDTO> getClientByName(String name) {
        return clientRepository.findByName(name).map(ClientMapper::toDTO);
    }

    public ClientDTO createClient(ClientDTO dto) {
        Client client = ClientMapper.toEntity(dto);
        client = clientRepository.save(client);
        return ClientMapper.toDTO(client);
    }

    public void deleteClientById(Integer id) {
        clientRepository.deleteById(id);
    }
}
