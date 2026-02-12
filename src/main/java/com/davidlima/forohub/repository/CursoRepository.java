package com.davidlima.forohub.repository;

import com.davidlima.forohub.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
