package dev.trajano.restaurante.dto;

public record AuthResponse(
        String token,
        String nome,
        String role
) {
}
