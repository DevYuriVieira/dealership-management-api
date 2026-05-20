package com.dealership.api.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.UUID;

public record VehicleRequest(
        @NotBlank(message = "Informe a placa do veículo.")
        @Length(max = 7, message = "A placa deve ter no máximo 7 caracteres.")
        String placa,

        @NotBlank(message = "Informe a marca do veículo.")
        String marca,

        @NotBlank(message = "Informe o modelo do veículo.")
        String modelo,

        @NotNull(message = "Informe o ano de fabricação.")
        @Positive(message = "O ano não pode ser um valor negativo.")
        Integer ano,

        @NotNull(message = "Informe o valor de tabela do veículo.")
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor do veículo deve ser maior que zero.")
        BigDecimal valor,

        @NotNull(message = "Defina o limite de desconto permitido.")
        @DecimalMin(value = "0.0", inclusive = true, message = "O valor de desconto não pode ser negativo.")
        BigDecimal maximoDesconto,

        @NotNull(message = "Indique o status atual do veículo (vendido ou não).")
        Boolean vendido,

        BigDecimal valorVenda,

        @NotNull(message = "É necessário vincular este veículo a um cliente válido.")
        UUID clientId
) {
}