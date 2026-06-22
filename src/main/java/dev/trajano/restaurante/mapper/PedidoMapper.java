package dev.trajano.restaurante.mapper;

import dev.trajano.restaurante.dto.PedidoRequest;
import dev.trajano.restaurante.dto.PedidoResponse;
import dev.trajano.restaurante.models.entity.Garcom;
import dev.trajano.restaurante.models.entity.Mesa;
import dev.trajano.restaurante.models.entity.Pedido;
import dev.trajano.restaurante.models.enums.StatusPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PedidoMapper {
    private final ItemPedidoMapper itemPedidoMapper;

    public Pedido toEntity(Mesa mesa,Garcom garcom, PedidoRequest request) {
        Pedido pedido = new Pedido();
        pedido.setMesa(mesa);
        pedido.setGarcom(garcom);
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setCriadoEm(LocalDateTime.now());
        pedido.setTotal(BigDecimal.ZERO);
        return pedido;
    }

    public PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getMesa().getId(),
                pedido.getGarcom().getNome(),
                pedido.getStatus(),
                pedido.getCriadoEm(),
                pedido.getFechadoEm(),
                pedido.getTotal(),
                pedido.getItens().stream()
                        .map(itemPedidoMapper::toResponse)
                        .toList()
        );
    }
}
