package com.devrodrigosnr.acaiteria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devrodrigosnr.acaiteria.model.Complemento;
import com.devrodrigosnr.acaiteria.service.ComplementoService;

@RestController
@RequestMapping("/api/complementos")
@CrossOrigin(origins = "*")
public class ComplementoController {

    private final ComplementoService complementoService;

    public ComplementoController(ComplementoService complementoService) {
        this.complementoService = complementoService;
    }

    @GetMapping
    public List<Complemento> listar() {
        return complementoService.listarComplementos();
    }

}
