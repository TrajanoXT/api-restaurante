package dev.trajano.restaurante.dto;

import java.math.BigDecimal;

public record ItemPedidoRequest(
        Long pedidoId,
        Long produtoId,
        Integer quantidade,
        BigDecimal precoUnitario,
        String observacao
) {
}
