package dev.trajano.restaurante.service;

import dev.trajano.restaurante.dto.AdicionarItemRequest;
import dev.trajano.restaurante.dto.PedidoRequest;
import dev.trajano.restaurante.dto.PedidoResponse;
import dev.trajano.restaurante.exceptions.BusinessException;
import dev.trajano.restaurante.exceptions.MethodArgumentNotValidException;
import dev.trajano.restaurante.exceptions.NotFoundException;
import dev.trajano.restaurante.mapper.PedidoMapper;
import dev.trajano.restaurante.domain.entity.*;
import dev.trajano.restaurante.domain.enums.StatusMesa;
import dev.trajano.restaurante.domain.enums.StatusPedido;
import dev.trajano.restaurante.repository.GarcomRepository;
import dev.trajano.restaurante.repository.MesaRepository;
import dev.trajano.restaurante.repository.PedidoRepository;
import dev.trajano.restaurante.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final MesaRepository mesaRepository;
    private final GarcomRepository garcomRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoResponse criarPedido(PedidoRequest request){
        if (request.mesaId() == null) throw new MethodArgumentNotValidException("O Id do mesa nao pode ser nula!");
        if (request.garcomId()==null) throw new MethodArgumentNotValidException("O Id do garcom nao pode ser nula!");
        Garcom garcom = garcomRepository.findById(request.garcomId()).orElseThrow(()-> new NotFoundException("Garcom nao encontrado!"));
        Mesa mesa = mesaRepository.findById(request.mesaId()).orElseThrow(()-> new NotFoundException("Mesa nao encontrado!"));
        mesa.setStatus(StatusMesa.OCUPADA);
        Pedido pedido=pedidoMapper.toEntity(mesa,garcom,request);
        mesaRepository.save(mesa);
        pedidoRepository.save(pedido);
        return pedidoMapper.toResponse(pedido);
    }
    public List<PedidoResponse> listarPedidos(){
        List<Pedido> pedidos=pedidoRepository.findAll();
        return pedidos.stream().map(pedidoMapper::toResponse).toList();
    }
    public PedidoResponse buscarPedidoPorId(Long id){
        Pedido pedido=pedidoRepository.findById(id).orElseThrow(()-> new NotFoundException("Pedido nao encontrado!"));
        return pedidoMapper.toResponse(pedido);
    }
    public PedidoResponse atualizarPedido(Long id,PedidoRequest request){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new NotFoundException("Pedido nao encontrado!"));
        if (pedido.getStatus()==StatusPedido.ENTREGUE||pedido.getStatus()==StatusPedido.CANCELADO) throw new MethodArgumentNotValidException("Pedido ja encerrado, nao pode ser alterado!");
        Garcom garcom = garcomRepository.findById(request.garcomId()).orElseThrow(()-> new NotFoundException("Garcom nao encontrado!"));
        pedido.setGarcom(garcom);
        pedidoRepository.save(pedido);
        return pedidoMapper.toResponse(pedido);
    }
    public PedidoResponse atualizarPedidoStatus(Long id,StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido nao encontrado!"));
        if (!pedido.getStatus().podeTracionarPara(status)) throw new MethodArgumentNotValidException("Status nao pode ser alterado!");
        if (status==StatusPedido.ENTREGUE||status==StatusPedido.CANCELADO) pedido.setFechadoEm(LocalDateTime.now());
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
        return pedidoMapper.toResponse(pedido);
    }
    public PedidoResponse adicionarItem(Long id, AdicionarItemRequest request){
        Pedido pedido =pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido nao encontrado!"));
        if (pedido.getStatus() != StatusPedido.ABERTO)
            throw new BusinessException("Status nao pode ser alterado!");
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto nao encontrado!"));
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(request.quantidade());
        item.setPrecoUnitario(produto.getPreco());
        item.setObservacao(request.observacao());
        pedido.getItens().add(item);
        recalcularValorTotal(pedido);
        pedidoRepository.save(pedido);
        return pedidoMapper.toResponse(pedido);
    }
    public PedidoResponse removerItem(Long pedidoId, Long itemId){
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new NotFoundException("Pedido nao encontrado!"));
        if (pedido.getStatus() != StatusPedido.ABERTO) throw new BusinessException("Status nao pode ser alterado!");
        if (pedido.getItens().size()==1) throw new BusinessException("Pedido deve ter ao menos 1 item");
        pedido.getItens().removeIf(i->i.getId().equals(itemId));
        recalcularValorTotal(pedido);
        pedidoRepository.save(pedido);
        return pedidoMapper.toResponse(pedido);
    }
    public void deletarPedido(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido nao encontrado!"));
    }
    public void recalcularValorTotal(Pedido pedido){
        BigDecimal total = pedido.getItens().stream().map(i->i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setTotal(total);
    }
}
