package com.devrodrigosnr.acaiteria.mappers;

import java.util.List;

import com.devrodrigosnr.acaiteria.dto.PedidoDTO;
import com.devrodrigosnr.acaiteria.model.Complemento;
import com.devrodrigosnr.acaiteria.model.Pedido;
import com.devrodrigosnr.acaiteria.repository.ComplementoRepository;

public class PedidoMapper {

    public static Pedido toEntity(PedidoDTO pedidoDTO, ComplementoRepository complementoRepository) {
        List<Complemento> complementos = complementoRepository.findAllById(pedidoDTO.complementoIds());
        return Pedido.builder()
                .tamanho(pedidoDTO.tamanho())
                .cobertura(pedidoDTO.cobertura())
                .complementos(complementos)
                .build();
    }

    public static PedidoDTO toDTO(Pedido pedido) {
        List<Long> complementoIds = pedido.getComplementos().stream()
                .map(Complemento::getId)
                .toList();
                
        return new PedidoDTO(
                pedido.getTamanho(),
                complementoIds,
                pedido.getCobertura()
        );
    }

}
