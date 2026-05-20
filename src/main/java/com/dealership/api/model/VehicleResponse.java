package com.dealership.api.model;

import java.math.BigDecimal;
import java.util.UUID;

public record VehicleResponse(
        UUID id,
        String placa,
        String marca,
        String modelo,
        Integer ano,
        BigDecimal valor,
        BigDecimal maximoDesconto,
        Boolean vendido,
        BigDecimal valorVenda,
        UUID clientId
) {
}
