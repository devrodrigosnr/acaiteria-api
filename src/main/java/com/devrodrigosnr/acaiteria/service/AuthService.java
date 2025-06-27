package com.devrodrigosnr.acaiteria.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.devrodrigosnr.acaiteria.dto.LoginRequest;
import com.devrodrigosnr.acaiteria.dto.LoginResponse;
import com.devrodrigosnr.acaiteria.model.User;
import com.devrodrigosnr.acaiteria.repository.UserRepository;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authManager, JwtService jwtService, UserRepository userRepository) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public LoginResponse autenticar(LoginRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        User user = userRepository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.gerarToken(
            org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getSenha())
                .roles(user.getPerfil().name())
                .build()
        );

        return new LoginResponse(token, user.getNome(), user.getPerfil());
    }
}
