package com.devrodrigosnr.acaiteria.dto;

import com.devrodrigosnr.acaiteria.PerfilEnum;

public record LoginResponse(String token, String nome, PerfilEnum perfil) {

}
