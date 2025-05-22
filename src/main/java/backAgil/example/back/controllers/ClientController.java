package backAgil.example.back.controllers;

import backAgil.example.back.models.Client;
import backAgil.example.back.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        if (client.getFullName() == null || client.getFullName().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        // Vérifier si un client avec le même fullName existe déjà
        if (clientRepository.findByFullName(client.getFullName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Conflit : client existe déjà
        }
        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    // Add new endpoint to fetch clients by IDs
    @GetMapping("/by-ids")
    public List<Client> getClientsByIds(@RequestParam List<Long> ids) {
        return clientRepository.findAllById(ids);
    }
}