package com.dealership.api.service;

import com.dealership.api.entity.Client;
import com.dealership.api.model.ClientRequest;
import com.dealership.api.model.ClientResponse;
import com.dealership.api.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponse inserir(ClientRequest request) {
        Client client = new Client();
        client.setCpf(request.cpf());
        client.setNome(request.nome());
        client.setEmail(request.email());
        client.setTelefone(request.telefone());

        Client savedClient = this.clientRepository.save(client);

        return new ClientResponse(
                savedClient.getId(),
                savedClient.getCpf(),
                savedClient.getNome(),
                savedClient.getEmail(),
                savedClient.getTelefone()
        );
    }

    public List<ClientResponse> buscarTodos() {
        return this.clientRepository.findAll()
                .stream()
                .map(client -> new ClientResponse(
                        client.getId(),
                        client.getCpf(),
                        client.getNome(),
                        client.getEmail(),
                        client.getTelefone()
                ))
                .collect(Collectors.toList());
    }

    public List<ClientResponse> buscarPorNomeOuCpf(String nome, String cpf) {
        if (cpf != null && !cpf.isBlank()) {
            return this.clientRepository.findByCpf(cpf)
                    .map(client -> new ClientResponse(
                            client.getId(),
                            client.getCpf(),
                            client.getNome(),
                            client.getEmail(),
                            client.getTelefone()
                    ))
                    .map(List::of)
                    .orElse(List.of());
        } else if (nome != null && !nome.isBlank()) {
            return this.clientRepository.findByNomeContainingIgnoreCase(nome)
                    .stream()
                    .map(client -> new ClientResponse(
                            client.getId(),
                            client.getCpf(),
                            client.getNome(),
                            client.getEmail(),
                            client.getTelefone()
                    ))
                    .collect(Collectors.toList());
        }
        return buscarTodos();
    }

    public void deletar(UUID id) {
        Client client = this.clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        this.clientRepository.delete(client);
    }
}
