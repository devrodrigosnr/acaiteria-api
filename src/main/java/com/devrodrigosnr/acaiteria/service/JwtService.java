package com.devrodrigosnr.acaiteria.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.devrodrigosnr.acaiteria.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private final int expirationTime = 3600000; // 1 hour in milliseconds

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String gerarToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("roles", List.of("ROLE_" + user.getPerfil().name()));

        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(user.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extrairEmail(String token) {
        return extrairTodosClaims(token).getSubject();
    }

    private Claims extrairTodosClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validarToken(String token, String email) {
        try {
            final String tokenEmail = extrairEmail(token);
            return tokenEmail.equals(email);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    public List<SimpleGrantedAuthority> extractRoles(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(secret.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody();

    List<String> roles = claims.get("roles", List.class);
    return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
