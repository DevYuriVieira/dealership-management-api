package com.dealership.api.model;

public record ClientRequest (
        String cpf,
        String nome,
        String email,
        String telefone
){

}
