package dev.trajano.restaurante.mapper;

import dev.trajano.restaurante.dto.ItemPedidoRequest;
import dev.trajano.restaurante.dto.ItemPedidoResponse;
import dev.trajano.restaurante.models.entity.ItemPedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemPedidoMapper {
    public ItemPedido toEntity(ItemPedidoRequest request){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setObservacao(request.observacao());
        itemPedido.setQuantidade(request.quantidade());
        itemPedido.setPrecoUnitario(request.precoUnitario());
        return itemPedido;
    }
    public ItemPedidoResponse toResponse(ItemPedido itemPedido){
        return new ItemPedidoResponse(
                itemPedido.getId(),
                itemPedido.getProduto().getNome(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getPrecoUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade())),
                itemPedido.getObservacao()
        );
    }
}
