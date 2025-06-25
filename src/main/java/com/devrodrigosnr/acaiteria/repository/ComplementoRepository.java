package com.devrodrigosnr.acaiteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devrodrigosnr.acaiteria.model.Complemento;

public interface ComplementoRepository extends JpaRepository<Complemento, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find a complemento by name:
    // Optional<Complemento> findByNome(String nome);   

}
