package dev.trajano.restaurante.dto;

import dev.trajano.restaurante.validation.Cpf;
import jakarta.validation.constraints.NotBlank;

public record GarcomRequest(
        @NotBlank
        String nome,
        @NotBlank
        @Cpf
        String cpf,
        Boolean ativo
) {
}
