package com.example.alura.forum.dtos.responses;

public record AuthResponseDto(
        String token,
        Long expiresIn,
        String refreshToken
) {
}
