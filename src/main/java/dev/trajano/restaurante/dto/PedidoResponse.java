package dev.trajano.restaurante.dto;

import dev.trajano.restaurante.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Long id,
        Long mesaId,
        String nomeGarcom,
        StatusPedido status,
        LocalDateTime criadoEm,
        LocalDateTime fechadoEm,
        BigDecimal total,
        List<ItemPedidoResponse> itens
) {}