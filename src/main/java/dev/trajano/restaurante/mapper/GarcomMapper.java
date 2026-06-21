package dev.trajano.restaurante.mapper;

import dev.trajano.restaurante.dto.GarcomRequest;
import dev.trajano.restaurante.dto.GarcomResponse;
import dev.trajano.restaurante.dto.ItemPedidoRequest;
import dev.trajano.restaurante.dto.MesaRequest;
import dev.trajano.restaurante.models.entity.Garcom;
import dev.trajano.restaurante.models.entity.ItemPedido;
import dev.trajano.restaurante.models.entity.Mesa;
import dev.trajano.restaurante.models.enums.StatusMesa;
import org.springframework.stereotype.Component;

@Component
public class GarcomMapper {
    public Garcom toEntityGarcom(GarcomRequest request){
        Garcom garcom = new Garcom();
        garcom.setNome(request.nome());
        garcom.setCpf(request.cpf());
        garcom.setAtivo(Boolean.TRUE);
        return garcom;
    }

    public GarcomResponse toResponseGarcom(Garcom garcom){
        return new GarcomResponse(
                garcom.getId(),
                garcom.getNome(),
                garcom.getCpf(),
                garcom.getAtivo()
        );
    }
}
