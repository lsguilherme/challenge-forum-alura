package com.example.alura.forum.services;

import com.example.alura.forum.dtos.requests.TopicoRequestDto;
import com.example.alura.forum.dtos.responses.TopicoResponseDto;
import com.example.alura.forum.exceptions.TopicoNotFoundException;
import com.example.alura.forum.mappers.TopicoMapper;
import com.example.alura.forum.repositories.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    private final TopicoMapper topicoMapper;

    public TopicoService(TopicoRepository topicoRepository, TopicoMapper topicoMapper) {
        this.topicoRepository = topicoRepository;
        this.topicoMapper = topicoMapper;
    }

    @Transactional
    public TopicoResponseDto criarTopico(TopicoRequestDto dto) {
        if (topicoRepository.findByTitulo(dto.titulo()).isPresent()) {
            throw new DataIntegrityViolationException("O título '" + dto.titulo() + "' já existe.");
        }
        var topico = topicoMapper.toEntity(dto);
        topicoRepository.save(topico);

        return topicoMapper.toDto(topico);
    }

    public TopicoResponseDto buscarTopicoPorId(Long id) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new TopicoNotFoundException("Topico não encontrado com id: " + id));
        return topicoMapper.toDto(topico);
    }

    @Transactional
    public void deletarTopicoPorId(Long id) {
        var topico = topicoRepository.findByIdAndAtivoIsTrue(id)
                .orElseThrow(() -> new TopicoNotFoundException("Tópico não encontrado com id: " + id));

        topico.setAtivo(false);
        topicoRepository.save(topico);
    }

    @Transactional
    public TopicoResponseDto atualizarTopicoPorId(Long id, TopicoRequestDto dto) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException("Tópico não encontrado com id: " + id));

        topicoMapper.updateTopicoFromDto(dto, topico);

        topicoRepository.save(topico);

        return topicoMapper.toDto(topico);
    }

    public Page<TopicoResponseDto> buscarTodosTopicos(Pageable pageable) {
        var topicos = topicoRepository.findAllByAtivoTrue(pageable);

        return topicos.map(topicoMapper::toDto);
    }
}
