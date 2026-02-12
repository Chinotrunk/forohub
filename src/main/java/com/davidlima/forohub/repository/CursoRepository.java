package com.davidlima.forohub.repository;

import com.davidlima.forohub.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Usuario, Long> {
}
