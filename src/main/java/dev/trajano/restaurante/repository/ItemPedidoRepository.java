package dev.trajano.restaurante.repository;

import dev.trajano.restaurante.models.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {
}
