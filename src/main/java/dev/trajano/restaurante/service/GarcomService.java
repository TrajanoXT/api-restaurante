package dev.trajano.restaurante.service;

import dev.trajano.restaurante.dto.GarcomRequest;
import dev.trajano.restaurante.dto.GarcomResponse;
import dev.trajano.restaurante.mapper.RestauranteMapper;
import dev.trajano.restaurante.repository.GarcomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GarcomService {
    private final GarcomRepository garcomRepository;
    private final RestauranteMapper restauranteMapper;

    public GarcomResponse criarGarcom(GarcomRequest request){
        if ()
    }
}
