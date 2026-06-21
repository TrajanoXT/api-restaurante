package dev.trajano.restaurante.dto;

public record GarcomResponse(
        Long id,
        String nome,
        String cpf,
        Boolean ativo
){}
