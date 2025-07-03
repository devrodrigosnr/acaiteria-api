package com.devrodrigosnr.acaiteria.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devrodrigosnr.acaiteria.dto.PedidoDetalheResponse;
import com.devrodrigosnr.acaiteria.enums.StatusPedido;
import com.devrodrigosnr.acaiteria.mappers.PedidoMapper;
import com.devrodrigosnr.acaiteria.model.Pedido;
import com.devrodrigosnr.acaiteria.service.PedidoService;

@RestController
@RequestMapping("/api/atendente/pedidos")
@PreAuthorize("hasRole('ROLE_ATENDENTE')")
public class AtendenteController {

    private final PedidoService pedidoService;

    public AtendenteController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDetalheResponse> listarPendentes() {
        List<Pedido> pedidos = pedidoService.listarPedidosPorStatus(StatusPedido.PENDENTE_PAGAMENTO);
        return PedidoMapper.toDetalheResponseList(pedidos);
    }

    @PutMapping("/{id}/enviar-cozinha")
    public void enviarParaCozinha(@PathVariable Long id) {
        pedidoService.alterarStatusPedido(id, StatusPedido.AGUARDANDO_PREPARO);
    }

    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
    }
}    

