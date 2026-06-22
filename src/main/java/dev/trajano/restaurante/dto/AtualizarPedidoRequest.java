package dev.trajano.restaurante.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarPedidoRequest(
        @NotNull(message = "Garcom e obrigatorio")
        Long garcomId
) {
}
