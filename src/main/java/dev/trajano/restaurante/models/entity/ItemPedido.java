package dev.trajano.restaurante.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Pedido pedido;
    @ManyToOne(optional = false)
    private Produto produto;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false,precision = 10,scale=2)
    private BigDecimal precoUnitario;
    private String observacao;
    public BigDecimal getTotalPreco(){
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
