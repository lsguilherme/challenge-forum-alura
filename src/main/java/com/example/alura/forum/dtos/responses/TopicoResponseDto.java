package com.example.alura.forum.dtos.responses;

import com.example.alura.forum.entities.Estado;

import java.time.LocalDateTime;

public record TopicoResponseDto(Long id, String titulo, String mensagem, LocalDateTime criadoEm, Estado estado, String autor, String curso) {
}
