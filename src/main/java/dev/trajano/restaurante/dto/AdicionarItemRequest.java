package dev.trajano.restaurante.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdicionarItemRequest(
        @NotNull(message = "Produto é obrigatório")
        Long produtoId,
        @NotNull @Min(value = 1, message = "Quantidade mínima é 1")
        Integer quantidade,
        @Size(max = 255)
        String observacao
) {}
