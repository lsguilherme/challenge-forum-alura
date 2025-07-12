package com.example.alura.forum.dtos.requests;

import com.example.alura.forum.entities.Estado;
import jakarta.validation.constraints.NotBlank;

public record TopicoRequestDto(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso,

        Estado estado
) {
}
