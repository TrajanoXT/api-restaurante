package dev.trajano.restaurante.domain.entity;

import dev.trajano.restaurante.domain.enums.StatusMesa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero;
    private Integer quantidade;
    @Enumerated(EnumType.STRING)
    private StatusMesa status;
}