package com.devrodrigosnr.acaiteria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devrodrigosnr.acaiteria.dto.ClienteDTO;
import com.devrodrigosnr.acaiteria.enums.StatusPedido;
import com.devrodrigosnr.acaiteria.model.Pedido;

@Service
public interface PedidoService {

    void salvarPedido(ClienteDTO clienteDTO);

    List<Pedido> listarPedidosPorStatus(StatusPedido statusPedido);

    void alterarStatusPedido(Long pedidoId, StatusPedido novoStatus);

    void excluirPedido(Long id);
}
