package com.devrodrigosnr.acaiteria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devrodrigosnr.acaiteria.enums.StatusPedido;
import com.devrodrigosnr.acaiteria.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findDistinctByStatusOrderByIdAsc(StatusPedido statusPedido);

}
