package com.dealership.api.model;

import java.util.UUID;

public record ClientResponse (
        UUID id,
        String cpf,
        String nome,
        String email,
        String telefone
) {
}
