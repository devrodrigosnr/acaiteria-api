package com.devrodrigosnr.acaiteria.dto;

import java.util.List;

public record PedidoDTO(String tamanho, List<Long> complementoIds, String cobertura) {

}
