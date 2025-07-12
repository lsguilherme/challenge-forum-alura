package com.example.alura.forum.controllers;

import com.example.alura.forum.dtos.requests.TopicoRequestDto;
import com.example.alura.forum.dtos.responses.TopicoResponseDto;
import com.example.alura.forum.services.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<TopicoResponseDto> criarTopico(@Valid @RequestBody TopicoRequestDto dto) {
        var topico = topicoService.criarTopico(dto);
        var uri = URI.create("/topicos/" + topico.id());
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TopicoResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(topicoService.buscarTopicoPorId(id));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        topicoService.deletarTopicoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<TopicoResponseDto> atualizarTopico(@PathVariable Long id, @RequestBody TopicoRequestDto dto){
        return ResponseEntity.ok(topicoService.atualizarTopicoPorId(id, dto));
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDto>> buscarTodosTopicos(
            @PageableDefault(sort = "criadoEm", direction = Sort.Direction.ASC) Pageable pageable
            ){
        return ResponseEntity.ok().body(topicoService.buscarTodosTopicos(pageable));
    }
}
