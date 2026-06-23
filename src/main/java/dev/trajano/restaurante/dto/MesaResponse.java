package dev.trajano.restaurante.dto;

import dev.trajano.restaurante.domain.enums.StatusMesa;

public record MesaResponse(
        Long id,
        Long numero,
        Integer quantidade,
        StatusMesa status
) {
}
