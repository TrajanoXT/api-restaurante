package dev.trajano.restaurante.service;

import dev.trajano.restaurante.dto.MesaRequest;
import dev.trajano.restaurante.dto.MesaResponse;
import dev.trajano.restaurante.exceptions.CpfAlreadyExistsException;
import dev.trajano.restaurante.exceptions.DataConflictException;
import dev.trajano.restaurante.mapper.MesaMapper;
import dev.trajano.restaurante.models.entity.Mesa;
import dev.trajano.restaurante.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MesaService {
    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;
    public MesaResponse criarMesa(MesaRequest mesaRequest) {
        if (mesaRepository.existsByNumero(mesaRequest.numero())){
            throw new DataConflictException("Numero da mesa ja existe.");
        }

    }
}
