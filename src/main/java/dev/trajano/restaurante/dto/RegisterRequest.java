package dev.trajano.restaurante.dto;

import dev.trajano.restaurante.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank String nome,
        @Email @NotBlank String email,
        @NotBlank String password,
        @NotNull Role role
){}