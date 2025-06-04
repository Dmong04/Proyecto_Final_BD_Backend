package com.una.controllers;

import com.una.dto.ClientDTO;
import com.una.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return service.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id) {
        return service.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClientDTO> getClientByName(@PathVariable String name) {
        return service.getClientByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO dto) {
        Optional<ClientDTO> found = service.getClientByName(dto.getName());
        if (found.isPresent()) {
            ResponseEntity.badRequest().build();
            return null;
        }
        return service.createClient(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Integer id, @RequestBody ClientDTO dto) {
        return service.getClientById(id)
                .map(client -> {
                    client.setName(dto.getName());
                    return ResponseEntity.ok(service.createClient(client));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> deleteClientById(@PathVariable Integer id) {
        Optional<ClientDTO> found = service.getClientById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteClientById(id);
        return ResponseEntity.ok().build();
    }
}
