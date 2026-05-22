package com.dealership.api.service;

import com.dealership.api.entity.Client;
import com.dealership.api.exception.DuplicateResourceException;
import com.dealership.api.exception.ResourceNotFoundException;
import com.dealership.api.model.ClientRequest;
import com.dealership.api.model.ClientResponse;
import com.dealership.api.repository.ClientRepository;
import org.springframework.stereotype.Service;

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
        String cpfSemMascara = request.cpf().replace(".", "").replace("-", "");

        if (this.clientRepository.existsByCpf(cpfSemMascara)) {
            throw new DuplicateResourceException("CPF já cadastrado: " + request.cpf());
        }
        if (this.clientRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("E-mail já cadastrado: " + request.email());
        }

        Client client = new Client();
        client.setCpf(cpfSemMascara);
        client.setNome(request.nome());
        client.setEmail(request.email());
        client.setTelefone(request.telefone());

        Client savedClient = this.clientRepository.save(client);

        return mapToResponse(savedClient);
    }

    public List<ClientResponse> buscarTodos() {
        return this.clientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ClientResponse> buscarPorNomeOuCpf(String nome, String cpf) {
        if (cpf != null && !cpf.isBlank()) {
            String cpfSemMascara = cpf.replace(".", "").replace("-", "");

            return this.clientRepository.findByCpf(cpfSemMascara)
                    .map(this::mapToResponse)
                    .map(List::of)
                    .orElse(List.of());
        } else if (nome != null && !nome.isBlank()) {
            return this.clientRepository.findByNomeContainingIgnoreCase(nome)
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }
        return buscarTodos();
    }

    public void deletar(UUID id) {
        Client client = this.clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));

        this.clientRepository.delete(client);
    }

    private ClientResponse mapToResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                formatarCpf(client.getCpf()),
                client.getNome(),
                client.getEmail(),
                client.getTelefone()
        );
    }

    private String formatarCpf(String cpf) {
        if (cpf != null && cpf.length() == 11) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpf;
    }
}
