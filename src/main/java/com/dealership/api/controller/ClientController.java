package com.dealership.api.controller;

import com.dealership.api.model.ClientRequest;
import com.dealership.api.model.ClientResponse;
import com.dealership.api.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> cadastrarCliente(@Valid @RequestBody ClientRequest request) {
        ClientResponse response = this.clientService.inserir(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> listarClientes() {
        List<ClientResponse> clientes = this.clientService.buscarTodos();
        return ResponseEntity.ok(clientes);
    }
}
