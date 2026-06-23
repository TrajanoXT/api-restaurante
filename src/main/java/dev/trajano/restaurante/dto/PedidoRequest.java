package dev.trajano.restaurante.dto;

import dev.trajano.restaurante.domain.entity.ItemPedido;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoRequest(
        @NotNull
        Long mesaId,
        @NotNull
        Long garcomId,
        List<ItemPedido> itens
){}