package dev.trajano.restaurante.controller;

import dev.trajano.restaurante.dto.MesaRequest;
import dev.trajano.restaurante.dto.MesaResponse;
import dev.trajano.restaurante.service.MesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mesas")
public class MesaController {
    private final MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaResponse> criarMesa(@Valid @RequestBody MesaRequest mesaRequest){
        return ResponseEntity.ok(mesaService.criarMesa(mesaRequest));
    }
    @PutMapping("/{mesaId}")
    public ResponseEntity<MesaResponse> atualizar(@PathVariable Long mesaId,@Valid @RequestBody MesaRequest request){
        return ResponseEntity.ok(mesaService.atualizarMesa(mesaId,request));
    }
    @GetMapping
    public ResponseEntity<List<MesaResponse>> listarMesas(){
        return ResponseEntity.ok(mesaService.listarMesas());
    }
    @GetMapping("/{mesaId}")
    public ResponseEntity<MesaResponse> buscarMesaPorId(@PathVariable Long mesaId){
        return ResponseEntity.ok(mesaService.buscarMesaPorId(mesaId));
    }
    @DeleteMapping("/{mesaId}")
    public ResponseEntity<Void> deletarMesaPorId(@PathVariable Long mesaId){
        mesaService.deletarMesaPorId(mesaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
