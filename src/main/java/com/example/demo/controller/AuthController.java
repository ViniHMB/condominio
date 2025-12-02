package com.example.demo.controller;

import java.util.Map;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService){
        this.authManager = authManager; this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        Authentication auth = authManager.authenticate(token);
        String jwt = jwtService.generateToken(auth);
        return ResponseEntity.ok(Map.of("token", jwt));
    }
}

public record AuthRequest(String username, String password) {}

