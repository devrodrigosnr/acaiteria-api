package com.devrodrigosnr.acaiteria.service;

import org.springframework.stereotype.Service;

import com.devrodrigosnr.acaiteria.dto.ClienteDTO;
import com.devrodrigosnr.acaiteria.mappers.ClienteMapper;
import com.devrodrigosnr.acaiteria.model.Cliente;
import com.devrodrigosnr.acaiteria.repository.ClienteRepository;
import com.devrodrigosnr.acaiteria.repository.ComplementoRepository;

@Service
public class PedidoService {

    private final ClienteRepository clienteRepository;
    private final ComplementoRepository complementoRepository;

    public PedidoService(ClienteRepository clienteRepository, ComplementoRepository complementoRepository) {
        this.clienteRepository = clienteRepository;
        this.complementoRepository = complementoRepository;
    }

    public void salvarPedido(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO, complementoRepository);
        clienteRepository.save(cliente);
    }

}
