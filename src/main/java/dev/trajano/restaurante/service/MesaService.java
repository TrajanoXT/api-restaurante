package dev.trajano.restaurante.service;

import dev.trajano.restaurante.dto.MesaRequest;
import dev.trajano.restaurante.dto.MesaResponse;
import dev.trajano.restaurante.exceptions.DataConflictException;
import dev.trajano.restaurante.exceptions.MethodArgumentNotValidException;
import dev.trajano.restaurante.exceptions.NotFoundException;
import dev.trajano.restaurante.mapper.MesaMapper;
import dev.trajano.restaurante.domain.entity.Mesa;
import dev.trajano.restaurante.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {
    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;
    public MesaResponse criarMesa(MesaRequest request) {
        if (mesaRepository.existsByNumero(request.numero())) throw new DataConflictException("Numero da mesa ja existe.");
        if (request.numero()<=0) throw new MethodArgumentNotValidException("O numero da mesa deve ser maior que zero.");
        if (request.quantidade()<=0) throw new MethodArgumentNotValidException("O quantidade deve ser maior que zero.");
        Mesa mesa = mesaMapper.toEntity(request);
        mesaRepository.save(mesa);
        return mesaMapper.toResponse(mesa);
    }
    public MesaResponse atualizarMesa(Long id, MesaRequest request){
        Mesa mesa = mesaRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Found Exception"));
        mesa.setNumero(request.numero());
        mesa.setQuantidade(request.quantidade());
        mesa.setStatus(request.status());
        mesaRepository.save(mesa);
        return mesaMapper.toResponse(mesa);
    }
    public List<MesaResponse> listarMesas(){
        List<Mesa> mesas = mesaRepository.findAll();
        return mesas.stream().map(mesaMapper::toResponse).toList();
    }
    public MesaResponse buscarMesaPorId(Long id){
        Mesa mesa = mesaRepository.findById(id).orElseThrow(()->new NotFoundException("Mesa Nao encontrada."));
        return mesaMapper.toResponse(mesa);
    }
    public void deletarMesaPorId(Long id){
        Mesa mesa = mesaRepository.findById(id).orElseThrow(() -> new NotFoundException("Mesa nao foi encontrada."));
        mesaRepository.delete(mesa);
    }
}