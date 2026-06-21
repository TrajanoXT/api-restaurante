package dev.trajano.restaurante.service;

import dev.trajano.restaurante.dto.GarcomRequest;
import dev.trajano.restaurante.dto.GarcomResponse;
import dev.trajano.restaurante.exceptions.CpfAlreadyExistsException;
import dev.trajano.restaurante.exceptions.NotFoundException;
import dev.trajano.restaurante.mapper.GarcomMapper;
import dev.trajano.restaurante.models.entity.Garcom;
import dev.trajano.restaurante.repository.GarcomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GarcomService {
    private final GarcomRepository garcomRepository;
    private final GarcomMapper garcomMapper;

    public GarcomResponse criarGarcom(GarcomRequest request){
        if (garcomRepository.existsByCpf(request.cpf())){
            throw new CpfAlreadyExistsException("Cpf is already exists");
        }
        Garcom garcom = garcomMapper.toEntityGarcom(request);
        garcomRepository.save(garcom);
        return garcomMapper.toResponseGarcom(garcom);
    }
    public GarcomResponse atualizarGarcom(Long id, GarcomRequest request){
        Garcom garcom = garcomRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not Found Exception"));
        garcom.setNome(request.nome());
        garcom.setCpf(request.cpf());
        garcom.setAtivo(request.ativo());
        garcomRepository.save(garcom);
        return garcomMapper.toResponseGarcom(garcom);
    }
    public List<GarcomResponse> listarGarcom(){
        List<Garcom> garcoms = garcomRepository.findAll();
        return garcoms.stream()
                .map(garcomMapper::toResponseGarcom)
                .toList();
    }
    public GarcomResponse buscarGarcomPorId(Long id){
        Garcom garcom = garcomRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Garcom nao encontrado."));
        return garcomMapper.toResponseGarcom(garcom);
    }

    public void deletarGarcomPorId(Long id){
        Garcom garcom = garcomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Garcom nao encontrado."));
        garcomRepository.delete(garcom);
    }
}
