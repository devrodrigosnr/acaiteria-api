package com.devrodrigosnr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.devrodrigosnr.acaiteria.controller.PedidoController;
import com.devrodrigosnr.acaiteria.dto.ClienteDTO;
import com.devrodrigosnr.acaiteria.dto.PedidoDTO;
import com.devrodrigosnr.acaiteria.dto.PedidoResponse;
import com.devrodrigosnr.acaiteria.service.PedidoService;

class PedidoControllerTest {

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveReceberPedidoComSucesso() {
        //Given
        ClienteDTO clienteDTO = new ClienteDTO(
                "João da Silva",
                "123456789",
                new ArrayList<PedidoDTO>()
        );

        //When
        ResponseEntity<PedidoResponse> response = pedidoController.receberPedido(clienteDTO);

        //Then
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Pedido recebido com sucesso!", response.getBody().mensagem());

        verify(pedidoService, times(1)).salvarPedido(clienteDTO);
    }
}
