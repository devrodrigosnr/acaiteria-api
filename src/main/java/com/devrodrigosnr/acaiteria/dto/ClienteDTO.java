package com.devrodrigosnr.acaiteria.dto;

import java.util.List;

public record ClienteDTO(String nome, String telefone, List<PedidoDTO> pedidos) {

}
