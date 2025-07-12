package com.example.alura.forum.controllers;

import com.example.alura.forum.dtos.requests.TopicoRequestDto;
import com.example.alura.forum.dtos.requests.responses.TopicoResponseDto;
import com.example.alura.forum.services.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoResponseDto> criarTopico(@Valid @RequestBody TopicoRequestDto dto) {
        var topico = topicoService.criarTopico(dto);
        return ResponseEntity.ok(topico);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TopicoResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(topicoService.buscarTopicoPorId(id));
    }

    @DeleteMapping(value = "{id}")
    @Transactional
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        topicoService.deletarTopicoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
