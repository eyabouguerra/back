package backAgil.example.back.services;

import backAgil.example.back.models.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client createClient(Client client);
    List<Client> getClientsByIds(List<Long> ids); // Add this
}