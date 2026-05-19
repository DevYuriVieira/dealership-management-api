package com.dealership.api.model;

import java.util.UUID;

public record VehicleResponse(
        UUID id,
        String placa,
        String marca,
        String modelo,
        Integer ano,
        UUID clientId
) {
}
