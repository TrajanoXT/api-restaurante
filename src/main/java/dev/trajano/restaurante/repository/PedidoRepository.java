package dev.trajano.restaurante.repository;

import dev.trajano.restaurante.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
