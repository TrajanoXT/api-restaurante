package dev.trajano.restaurante.mapper;

import dev.trajano.restaurante.dto.ProdutoRequest;
import dev.trajano.restaurante.dto.ProdutoResponse;
import dev.trajano.restaurante.domain.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public Produto toEntity(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setCategoria(request.categoria());
        produto.setDisponivel(request.disponivel());
        return produto;
    }
    public ProdutoResponse toResponse(Produto produto){
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.getDisponivel()
        );
    }

    public Produto toUpdateResponse(Produto produto,ProdutoRequest request){
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setCategoria(request.categoria());
        produto.setDisponivel(request.disponivel());
        return produto;
    }
}
