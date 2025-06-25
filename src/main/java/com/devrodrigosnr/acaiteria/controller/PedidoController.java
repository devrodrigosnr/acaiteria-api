package com.devrodrigosnr.acaiteria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devrodrigosnr.acaiteria.dto.ClienteDTO;
import com.devrodrigosnr.acaiteria.dto.PedidoResponse;
import com.devrodrigosnr.acaiteria.service.PedidoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping()
    public ResponseEntity<PedidoResponse> receberPedido(@RequestBody ClienteDTO clienteDTO) {
        pedidoService.salvarPedido(clienteDTO);
        return ResponseEntity.ok(new PedidoResponse("Pedido recebido com sucesso!"));
    }
}
