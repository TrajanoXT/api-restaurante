package dev.trajano.restaurante.controller;

import dev.trajano.restaurante.dto.ProdutoRequest;
import dev.trajano.restaurante.dto.ProdutoResponse;
import dev.trajano.restaurante.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@Valid @RequestBody ProdutoRequest produtoRequest){
        return ResponseEntity.ok(produtoService.criarProduto(produtoRequest));
    }
    @PutMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Long produtoId, @Valid @RequestBody ProdutoRequest produtoRequest){
        return ResponseEntity.ok(produtoService.atualizarProduto(produtoId, produtoRequest));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProdutoResponse> buscarProdutoPorId(@PathVariable Long productId){
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(productId));
    }
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> buscarProdutos(){
        return ResponseEntity.ok(produtoService.listarProdutos());
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long productId){
        produtoService.excluirProduto(productId);
        return ResponseEntity.noContent().build();
    }
}