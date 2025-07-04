package com.devrodrigosnr.acaiteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devrodrigosnr.acaiteria.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
