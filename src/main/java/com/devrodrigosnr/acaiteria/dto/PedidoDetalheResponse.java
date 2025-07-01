package com.devrodrigosnr.acaiteria.dto;

import java.util.List;

import com.devrodrigosnr.acaiteria.enums.StatusPedido;

public record PedidoDetalheResponse(
    Long id,
    String tamanho,
    String cobertura,
    List<String> complementos,
    StatusPedido status
    ) {
}
