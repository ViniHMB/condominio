package com.example.demo.service;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

import javax.crypto.SecretKey;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final SecretKey key = Keys.hmacShaKeyFor("SENHA_SUPER_SECRETA_32_CHARES_MIN".getBytes());
    public String generateToken(Authentication auth) {
        Instant now = Instant.now();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts.builder()
            .setSubject(auth.name())
            .claim("authorities", roles) // guarda roles/permissions no claim
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(2, ChronoUnit.HOURS)))
            .signWith(key)
            .compact();
    }

    public JwtParser parser() { return Jwts.parserBuilder().setSigningKey(key).build(); }
}
