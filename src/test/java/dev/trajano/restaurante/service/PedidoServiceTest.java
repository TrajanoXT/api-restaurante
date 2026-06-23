package dev.trajano.restaurante.service;

import dev.trajano.restaurante.domain.entity.ItemPedido;
import dev.trajano.restaurante.domain.entity.Pedido;
import dev.trajano.restaurante.domain.enums.StatusPedido;
import dev.trajano.restaurante.dto.AdicionarItemRequest;
import dev.trajano.restaurante.exceptions.BusinessException;
import dev.trajano.restaurante.exceptions.NotFoundException;
import dev.trajano.restaurante.mapper.PedidoMapper;
import dev.trajano.restaurante.repository.GarcomRepository;
import dev.trajano.restaurante.repository.MesaRepository;
import dev.trajano.restaurante.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private GarcomRepository garcomRepository;
    @Mock
    private MesaRepository mesaRepository;
    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void deveLancarExcecaoQuandoPedidoNaoEncontrado(){
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->pedidoService.buscarPedidoPorId(99L));
    }
    @Test
    void naoDeveAvancarStatusinvalido(){
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.ENTREGUE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        assertThrows(BusinessException.class,()->
                pedidoService.adicionarItem(1L,new AdicionarItemRequest(1L,2,null)));
    }
    @Test
    void naoDeveRemoverUnicoItem(){
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.ABERTO);
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(1L);
        pedido.getItens().add(itemPedido);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        assertThrows(BusinessException.class,()->
                pedidoService.removerItem(1L,1L));
    }
}
