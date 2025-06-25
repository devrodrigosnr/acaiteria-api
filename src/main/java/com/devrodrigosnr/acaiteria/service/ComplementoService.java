package com.devrodrigosnr.acaiteria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devrodrigosnr.acaiteria.model.Complemento;
import com.devrodrigosnr.acaiteria.repository.ComplementoRepository;

@Service
public class ComplementoService {

    private final ComplementoRepository complementoRepository;

    public ComplementoService(ComplementoRepository complementoRepository) {
        this.complementoRepository = complementoRepository;
    }

    public List<Complemento> listarComplementos() {
        return complementoRepository.findAll();
    }

}
