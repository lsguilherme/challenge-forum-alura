package com.example.alura.forum.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record TopicoRequestDto(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
