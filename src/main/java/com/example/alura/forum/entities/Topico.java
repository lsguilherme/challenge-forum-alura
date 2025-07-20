package com.example.alura.forum.entities;

import com.example.alura.forum.enums.Estado;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime criadoEm;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private String autor;

    private String curso;

    private Boolean ativo;

    public Topico() {
    }

    public Topico(String titulo, String mensagem, Estado estado, String autor, String curso, Boolean ativo) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.estado = estado;
        this.autor = autor;
        this.curso = curso;
        this.ativo = ativo;
    }

    @PrePersist
    public void preencherDadosDeCriacao() {
        this.criadoEm = LocalDateTime.now();
        this.estado = Estado.ABERTO;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
