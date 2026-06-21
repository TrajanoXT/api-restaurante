package dev.trajano.restaurante.mapper;

import dev.trajano.restaurante.dto.ItemPedidoRequest;
import dev.trajano.restaurante.models.entity.ItemPedido;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {
    public ItemPedido toEntityItemPedido(ItemPedidoRequest request){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setObservacao(request.observacao());
        itemPedido.setQuantidade(request.quantidade());
        itemPedido.setPrecoUnitario(request.precoUnitario());
        return itemPedido;
    }

}
