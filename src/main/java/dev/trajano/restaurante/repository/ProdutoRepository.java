package dev.trajano.restaurante.repository;

import dev.trajano.restaurante.models.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
