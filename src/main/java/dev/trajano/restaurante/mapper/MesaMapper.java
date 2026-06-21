package dev.trajano.restaurante.mapper;

import dev.trajano.restaurante.dto.MesaRequest;
import dev.trajano.restaurante.dto.MesaResponse;
import dev.trajano.restaurante.models.entity.Mesa;
import dev.trajano.restaurante.models.enums.StatusMesa;
import org.springframework.stereotype.Component;

@Component
public class MesaMapper {
    public Mesa toEntity(MesaRequest request){
        Mesa mesa = new Mesa();
        mesa.setNumero(request.numero());
        mesa.setQuantidade(request.quantidade());
        mesa.setStatus(StatusMesa.LIVRE);
        return mesa;
    }

     public MesaResponse toResponse(Mesa mesa){
        return new MesaResponse(
                mesa.getId(),
                mesa.getNumero(),
                mesa.getQuantidade(),
                mesa.getStatus()
        );
     }
}
