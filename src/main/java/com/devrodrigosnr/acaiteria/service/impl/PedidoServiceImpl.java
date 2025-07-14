package com.devrodrigosnr.acaiteria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devrodrigosnr.acaiteria.dto.ClienteDTO;
import com.devrodrigosnr.acaiteria.enums.StatusPedido;
import com.devrodrigosnr.acaiteria.mappers.ClienteMapper;
import com.devrodrigosnr.acaiteria.model.Cliente;
import com.devrodrigosnr.acaiteria.model.Pedido;
import com.devrodrigosnr.acaiteria.repository.ClienteRepository;
import com.devrodrigosnr.acaiteria.repository.ComplementoRepository;
import com.devrodrigosnr.acaiteria.repository.PedidoRepository;
import com.devrodrigosnr.acaiteria.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
    
    private final ClienteRepository clienteRepository;
    private final ComplementoRepository complementoRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(ClienteRepository clienteRepository, 
                            ComplementoRepository complementoRepository,
                            PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.complementoRepository = complementoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void salvarPedido(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO, complementoRepository);
        clienteRepository.save(cliente);
    }

    @Override
    public List<Pedido> listarPedidosPorStatus(StatusPedido statusPedido) {
        return pedidoRepository.findDistinctByStatusOrderByIdAsc(statusPedido);
    }

    @Override
    public void alterarStatusPedido(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(novoStatus);
        pedidoRepository.save(pedido);
    }

    @Override
    public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
