package com.example.alura.forum.dtos.requests;

public record AuthRequestDto(
        String login,
        String password
) {
}
