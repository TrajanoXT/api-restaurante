package dev.trajano.restaurante.repository;

import dev.trajano.restaurante.domain.entity.Garcom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarcomRepository extends JpaRepository<Garcom,Long> {
    boolean existsByCpf(String cpf);
}
