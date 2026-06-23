package dev.trajano.restaurante.dto;

import dev.trajano.restaurante.domain.enums.CategoriaProduto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        CategoriaProduto categoria,
        Boolean disponivel
){}
