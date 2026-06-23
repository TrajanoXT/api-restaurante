package dev.trajano.restaurante.controller;

import dev.trajano.restaurante.dto.AdicionarItemRequest;
import dev.trajano.restaurante.dto.PedidoRequest;
import dev.trajano.restaurante.dto.PedidoResponse;
import dev.trajano.restaurante.domain.enums.StatusPedido;
import dev.trajano.restaurante.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@Valid @RequestBody PedidoRequest request){
        return ResponseEntity.ok(pedidoService.criarPedido(request));
    }
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarPedidos(){
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizar(@PathVariable Long id,
                                                    @Valid @RequestBody PedidoRequest request){
        return ResponseEntity.ok(pedidoService.atualizarPedido(id,request));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponse> avancarStatus(@PathVariable Long id,
                                                        @RequestParam StatusPedido status){
        return ResponseEntity.ok(pedidoService.atualizarPedidoStatus(id,status));
    }
    @PostMapping("/{id}/itens")
    public ResponseEntity<PedidoResponse> adicionarItem(@PathVariable Long id, @Valid @RequestBody AdicionarItemRequest request){
        return ResponseEntity.ok(pedidoService.adicionarItem(id,request));
    }
    @DeleteMapping("/{pedidoId}/itens/{itemid}")
    public ResponseEntity<PedidoResponse> removerItem(@PathVariable Long pedidoId, @PathVariable Long itemId){
        return ResponseEntity.ok(pedidoService.removerItem(pedidoId,itemId));
    }
    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long pedidoId){
        pedidoService.deletarPedido(pedidoId);
        return ResponseEntity.ok().build();
    }
}
