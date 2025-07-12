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

    public void deletarTopicoPorId(Long id) {
        var topico = topicoRepository.findByIdAndAtivoIsTrue(id)
                .orElseThrow(() -> new TopicoException("Tópico não encontrado com id: " + id));

        topico.setAtivo(false);
        topicoRepository.save(topico);
    }

    public TopicoResponseDto atualizarTopicoPorId(Long id, TopicoRequestDto dto) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoException("Tópico não encontrado com id: " + id));

        topicoMapper.updateTopicoFromDto(dto, topico);

        topicoRepository.save(topico);

        return topicoMapper.toDto(topico);
    }
}
