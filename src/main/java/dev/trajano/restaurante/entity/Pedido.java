package dev.trajano.restaurante.entity;

import dev.trajano.restaurante.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;
    @ManyToOne(optional = false)
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;
    @Column(nullable = false)
    private LocalDateTime criadoEm;
    private LocalDateTime fechadoEm;
    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal total;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ItemPedido> itens=new ArrayList<>();
}
