package dev.trajano.restaurante.repository;

import dev.trajano.restaurante.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
