package dev.trajano.restaurante.repository;

import dev.trajano.restaurante.domain.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    boolean existsByNumero(Long numero);
}
