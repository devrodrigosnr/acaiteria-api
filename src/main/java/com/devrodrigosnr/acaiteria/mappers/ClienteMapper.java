package com.devrodrigosnr.acaiteria.mappers;

import java.util.List;

import com.devrodrigosnr.acaiteria.dto.ClienteDTO;
import com.devrodrigosnr.acaiteria.dto.PedidoDTO;
import com.devrodrigosnr.acaiteria.enums.StatusPedido;
import com.devrodrigosnr.acaiteria.model.Cliente;
import com.devrodrigosnr.acaiteria.model.Pedido;
import com.devrodrigosnr.acaiteria.repository.ComplementoRepository;

public class ClienteMapper {

    public static Cliente toEntity(ClienteDTO clienteDTO, ComplementoRepository complementoRepository) {

        Cliente cliente = Cliente.builder()
                .nome(clienteDTO.nome())
                .telefone(clienteDTO.telefone())
                .build();

        List<Pedido> pedidos = clienteDTO.pedidos().stream()
                .map(p -> {
                    Pedido pedido = PedidoMapper.toEntity(p, complementoRepository);
                    pedido.setCliente(cliente);
                    pedido.setStatus(StatusPedido.PENDENTE_PAGAMENTO);
                    return pedido;
                }).toList();

        cliente.setPedidos(pedidos);
        return cliente;
    }

    public static ClienteDTO toDTO(Cliente cliente) {

        List<PedidoDTO> pedidosDTO = cliente.getPedidos().stream()
                .map(PedidoMapper::toDTO)
                .toList();

        return new ClienteDTO(
                cliente.getNome(),
                cliente.getTelefone(),
                pedidosDTO
        );
    }
}
