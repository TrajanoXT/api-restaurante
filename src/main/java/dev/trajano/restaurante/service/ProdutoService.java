package dev.trajano.restaurante.service;

import dev.trajano.restaurante.dto.ProdutoRequest;
import dev.trajano.restaurante.dto.ProdutoResponse;
import dev.trajano.restaurante.exceptions.MethodArgumentNotValidException;
import dev.trajano.restaurante.exceptions.NotFoundException;
import dev.trajano.restaurante.mapper.ProdutoMapper;
import dev.trajano.restaurante.domain.entity.Produto;
import dev.trajano.restaurante.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoMapper produtoMapper;
    private final ProdutoRepository produtoRepository;
    public ProdutoResponse criarProduto(ProdutoRequest produtoRequest) {
        if (produtoRequest.nome()==null||produtoRequest.nome().isBlank()) throw new MethodArgumentNotValidException("Nome nao pode ser vazio.");
        if (produtoRequest.descricao()==null||produtoRequest.descricao().isBlank())throw new MethodArgumentNotValidException("Descricao nao pode ser vazio.");
        if (produtoRequest.preco().compareTo(BigDecimal.ZERO)<0)throw new MethodArgumentNotValidException("Preco nao pode ser menor ou igual a zero.");
        if (produtoRequest.categoria()==null)throw new MethodArgumentNotValidException("Categoria nao pode ser vazio.");
        Produto produto = produtoMapper.toEntity(produtoRequest);
        produtoRepository.save(produto);
        return produtoMapper.toResponse(produto);
    }
    public List<ProdutoResponse> listarProdutos(){
        List<Produto> produto = produtoRepository.findAll();
        return produto.stream().map(produtoMapper::toResponse).toList();
    }
    public ProdutoResponse atualizarProduto(Long id,ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id).orElseThrow(()->new NotFoundException("Produto nao encontrado."));
        Produto update = produtoMapper.toUpdateResponse(produto, request);
        produtoRepository.save(update);
        return produtoMapper.toResponse(produto);
    }
    public ProdutoResponse buscarProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(()->new NotFoundException("Produto nao encontrado."));
        return produtoMapper.toResponse(produto);
    }
    public void excluirProduto(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(()->new NotFoundException("Produto nao encontrado."));
        produtoRepository.delete(produto);
    }
}