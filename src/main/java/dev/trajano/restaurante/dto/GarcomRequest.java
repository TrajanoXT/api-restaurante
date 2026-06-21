package dev.trajano.restaurante.dto;

public record GarcomRequest(
        String nome,
        String cpf,
        Boolean ativo
) {
}
