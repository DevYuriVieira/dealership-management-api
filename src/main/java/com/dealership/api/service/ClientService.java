package com.dealership.api.service;

import com.dealership.api.entity.Client;
import com.dealership.api.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client inserir(Client client) {
        return this.clientRepository.save(client);
    }
}
