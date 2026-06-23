package dev.trajano.restaurante.dto;

import dev.trajano.restaurante.domain.enums.StatusMesa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MesaRequest(
        @NotNull
        @Positive(message = "O numero precisa ser maior que 0.")
        Long numero,
        @NotNull
        @Positive(message = "A quantidade precisa ser maior.")
        Integer quantidade,
        StatusMesa status
) {
}
