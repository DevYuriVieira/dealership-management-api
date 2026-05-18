package com.dealership.api.controller;

import com.dealership.api.entity.Client;
import com.dealership.api.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    private ClientService clienteservice;

    public ClientController(ClientService clienteservice) {
        this.clienteservice = clienteservice;
    }

    @PostMapping
    public ResponseEntity<Client> cadastrarCliente(@RequestBody Client client) {
        Client clienteSalvo = this.clienteservice.inserir(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

}
