package com.davidlima.forohub.service;

import com.davidlima.forohub.controller.usuario.dto.UsuarioRequest;
import com.davidlima.forohub.controller.usuario.dto.UsuarioResponse;
import com.davidlima.forohub.domain.Perfil;
import com.davidlima.forohub.domain.Usuario;
import com.davidlima.forohub.repository.PerfilRepository;
import com.davidlima.forohub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public UsuarioResponse crearUsuario(UsuarioRequest request) {
    if (usuarioRepository.findByCorreoElectronico(request.correoElectronico()).isPresent()) {
      throw new IllegalArgumentException("El correo electrónico ya está registrado");
    }

    // Asignar perfil por defecto "ROLE_USER" si existe
    Set<Perfil> perfiles = new HashSet<>();
    perfilRepository.findById(1L).ifPresent(perfiles::add); // ajusta según tu BD

    Usuario usuario = Usuario.builder()
            .nombre(request.nombre())
            .correoElectronico(request.correoElectronico())
            .contrasena(passwordEncoder.encode(request.contrasena()))
            .perfiles(perfiles)
            .build();

    Usuario guardado = usuarioRepository.save(usuario);
    return toResponse(guardado);
  }

  public Page<UsuarioResponse> listarUsuarios(Pageable pageable) {
    return usuarioRepository.findAll(pageable)
            .map(this::toResponse);
  }

  public UsuarioResponse obtenerPorId(Long id) {
    Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    return toResponse(usuario);
  }

  @Transactional
  public void eliminarUsuario(Long id) {
    if (!usuarioRepository.existsById(id)) {
      throw new EntityNotFoundException("Usuario no encontrado");
    }
    usuarioRepository.deleteById(id);
  }

  private UsuarioResponse toResponse(Usuario u) {
    Set<String> nombrePerfiles = u.getPerfiles().stream()
            .map(Perfil::getNombre)
            .collect(Collectors.toSet());
    return new UsuarioResponse(u.getId(), u.getNombre(), u.getCorreoElectronico(), nombrePerfiles);
  }
}
