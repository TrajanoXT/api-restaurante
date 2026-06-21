package dev.trajano.restaurante.mapper;

import dev.trajano.restaurante.dto.GarcomRequest;
import dev.trajano.restaurante.dto.ItemPedidoRequest;
import dev.trajano.restaurante.dto.MesaRequest;
import dev.trajano.restaurante.entity.Garcom;
import dev.trajano.restaurante.entity.ItemPedido;
import dev.trajano.restaurante.entity.Mesa;
import dev.trajano.restaurante.enums.StatusMesa;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMapper {
    public Garcom toEntityGarcom(GarcomRequest request){
        Garcom garcom = new Garcom();
        garcom.setNome(request.nome());
        garcom.setCpf(request.cpf());
        garcom.setAtivo(request.ativo());
        return garcom;
    }

    public ItemPedido toEntityItemPedido(ItemPedidoRequest request){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setObservacao(request.observacao());
        itemPedido.setQuantidade(request.quantidade());
        itemPedido.setPrecoUnitario(request.precoUnitario());
        return itemPedido;
    }

    public Mesa toEntityMesa(MesaRequest request){
        Mesa mesa = new Mesa();
        mesa.setNumero(request.numero());
        mesa.setQuantidade(request.quantidade());
        mesa.setStatus(StatusMesa.LIVRE);
        return mesa;
    }
}
