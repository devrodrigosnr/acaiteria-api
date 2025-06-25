package com.devrodrigosnr.acaiteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devrodrigosnr.acaiteria.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Custom query methods can be defined here if needed
    // For example, to find a client by name:
    // Optional<Cliente> findByNome(String nome);

}
