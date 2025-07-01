package com.devrodrigosnr.acaiteria.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/cozinha/pedidos")
@PreAuthorize("hasRole('COZINHA')")
public class CozinhaController {

    private final PedidoService pedidoService;

    public CozinhaController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDetalheResponse> listarAguardandoPreparo() {
        List<Pedido> pedidos = pedidoService.listarPedidosPorStatus(StatusPedido.AGUARDANDO_PREPARO);
        return PedidoMapper.toDetalheResponseList(pedidos);
    }

    @PutMapping("/{id}/marcar-pronto")
    public void marcarComoPronto(@PathVariable Long id) {
        pedidoService.alterarStatusPedido(id, StatusPedido.PRONTO);
    }

}
