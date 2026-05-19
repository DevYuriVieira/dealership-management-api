package com.dealership.api.model;

import java.util.UUID;

public record VehicleRequest (
        String placa,
        String marca,
        String modelo,
        Integer ano,
        UUID clientId
){
}
