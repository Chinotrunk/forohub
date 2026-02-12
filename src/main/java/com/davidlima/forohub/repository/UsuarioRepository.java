package com.davidlima.forohub.repository;

import com.davidlima.forohub.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}
