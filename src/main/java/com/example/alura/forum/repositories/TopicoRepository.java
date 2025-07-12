package com.example.alura.forum.repositories;

import com.example.alura.forum.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByIdAndAtivoIsTrue(Long id);
}
