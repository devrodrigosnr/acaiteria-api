package com.devrodrigosnr.acaiteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devrodrigosnr.acaiteria.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    // Custom query methods can be defined here if needed
    // For example, to find a pedido by tamanho:
    // Optional<Pedido> findByTamanho(String tamanho);
    
    // You can also define methods to find pedidos by cliente, etc.

}
