package com.example.alura.forum.controllers;

import com.example.alura.forum.dtos.requests.AuthRequestDto;
import com.example.alura.forum.dtos.responses.AuthResponseDto;
import com.example.alura.forum.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<AuthResponseDto> autenticarUsuario(@RequestBody AuthRequestDto dto){
        return ResponseEntity.ok(authService.autenticar(dto));
    }

}
