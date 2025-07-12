package com.example.alura.forum.services;

import com.example.alura.forum.dtos.requests.TopicoRequestDto;
import com.example.alura.forum.dtos.requests.responses.TopicoResponseDto;
import com.example.alura.forum.exceptions.TopicoException;
import com.example.alura.forum.mappers.TopicoMapper;
import com.example.alura.forum.repositories.TopicoRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    private final TopicoMapper topicoMapper;

    public TopicoService(TopicoRepository topicoRepository, TopicoMapper topicoMapper) {
        this.topicoRepository = topicoRepository;
        this.topicoMapper = topicoMapper;
    }

    public TopicoResponseDto criarTopico(TopicoRequestDto dto) {
        var topico = topicoMapper.toEntity(dto);
        topicoRepository.save(topico);

        return topicoMapper.toDto(topico);
    }

    public TopicoResponseDto buscarTopicoPorId(Long id) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new TopicoException("Topico não encontrado com id: " + id));
        return topicoMapper.toDto(topico);
    }
}
