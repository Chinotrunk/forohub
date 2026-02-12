package com.davidlima.forohub.service;

import com.davidlima.forohub.controller.topico.dto.TopicoRequest;
import com.davidlima.forohub.controller.topico.dto.TopicoResponse;
import com.davidlima.forohub.controller.topico.dto.TopicoUpdateRequest;
import com.davidlima.forohub.domain.Curso;
import com.davidlima.forohub.domain.Topico;
import com.davidlima.forohub.domain.Usuario;
import com.davidlima.forohub.repository.CursoRepository;
import com.davidlima.forohub.repository.TopicoRepository;
import com.davidlima.forohub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TopicoService {

  private final TopicoRepository topicoRepository;
  private final UsuarioRepository usuarioRepository;
  private final CursoRepository cursoRepository;

  @Transactional
  public TopicoResponse crearTopico(TopicoRequest request) {
    if (topicoRepository.existsByTituloAndMensaje(request.titulo(), request.mensaje())) {
      throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
    }

    Usuario autor = usuarioRepository.findById(request.autorId())
            .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
    Curso curso = cursoRepository.findById(request.cursoId())
            .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

    Topico topico = Topico.builder()
            .titulo(request.titulo())
            .mensaje(request.mensaje())
            .autor(autor)
            .curso(curso)
            .status("ABIERTO")
            .fechaCreacion(LocalDateTime.now())
            .build();

    Topico guardado = topicoRepository.save(topico);
    return toResponse(guardado);
  }

  public Page<TopicoResponse> listarTopicos(Pageable pageable) {
    return topicoRepository.findAll(pageable)
            .map(this::toResponse);
  }

  public TopicoResponse obtenerPorId(Long id) {
    Topico topico = topicoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
    return toResponse(topico);
  }

  @Transactional
  public TopicoResponse actualizarTopico(Long id, TopicoUpdateRequest request) {
    Topico topico = topicoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

    if (topicoRepository.existsByTituloAndMensaje(request.titulo(), request.mensaje())
            && (!topico.getTitulo().equals(request.titulo()) || !topico.getMensaje().equals(request.mensaje()))) {
      throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
    }

    topico.setTitulo(request.titulo());
    topico.setMensaje(request.mensaje());
    return toResponse(topico);
  }

  @Transactional
  public void eliminarTopico(Long id) {
    if (!topicoRepository.existsById(id)) {
      throw new EntityNotFoundException("Tópico no encontrado");
    }
    topicoRepository.deleteById(id);
  }

  private TopicoResponse toResponse(Topico t) {
    return new TopicoResponse(
            t.getId(),
            t.getTitulo(),
            t.getMensaje(),
            t.getFechaCreacion(),
            t.getStatus(),
            t.getAutor().getNombre(),
            t.getCurso().getNombre()
    );
  }
}
