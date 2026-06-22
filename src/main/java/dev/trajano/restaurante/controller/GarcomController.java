package dev.trajano.restaurante.controller;

import dev.trajano.restaurante.dto.GarcomRequest;
import dev.trajano.restaurante.dto.GarcomResponse;
import dev.trajano.restaurante.service.GarcomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/garcons")
public class GarcomController {
    private final GarcomService garcomService;

    @PostMapping
    public ResponseEntity<GarcomResponse> criarGarcom(@Valid @RequestBody GarcomRequest request){
        return ResponseEntity.ok(garcomService.criarGarcom(request));
    }
    @GetMapping
    public ResponseEntity<List<GarcomResponse>> listarGarcom(){
        return ResponseEntity.ok(garcomService.listarGarcom());
    }
    @PutMapping("/{garcomId}")
    public ResponseEntity<GarcomResponse> atualizarGarcom(@PathVariable Long garcomId,@Valid @RequestBody GarcomRequest request){
        return ResponseEntity.ok(garcomService.atualizarGarcom(garcomId, request));
    }
    @DeleteMapping("/{garcomId}")
    public ResponseEntity<GarcomResponse> deletarGarcom(@PathVariable Long garcomId){
        garcomService.deletarGarcomPorId(garcomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/{garcomId}")
    public ResponseEntity<GarcomResponse> buscarGarcomPorId(@PathVariable Long garcomId){
        return ResponseEntity.ok(garcomService.buscarGarcomPorId(garcomId));
    }
}