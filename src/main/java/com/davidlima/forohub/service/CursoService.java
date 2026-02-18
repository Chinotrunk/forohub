package com.davidlima.forohub.service;

import com.davidlima.forohub.controller.curso.dto.CursoRequest;
import com.davidlima.forohub.controller.curso.dto.CursoResponse;
import com.davidlima.forohub.domain.Curso;
import com.davidlima.forohub.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursoService {

  private final CursoRepository cursoRepository;

  @Transactional
  public CursoResponse crearCurso(CursoRequest request) {
    Curso curso = Curso.builder()
            .nombre(request.nombre())
            .categoria(request.categoria())
            .build();

    Curso guardado = cursoRepository.save(curso);
    return toResponse(guardado);
  }

  public Page<CursoResponse> listarCursos(Pageable pageable) {
    return cursoRepository.findAll(pageable)
            .map(this::toResponse);
  }

  public CursoResponse obtenerPorId(Long id) {
    Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
    return toResponse(curso);
  }

  @Transactional
  public CursoResponse actualizarCurso(Long id, CursoRequest request) {
    Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

    curso.setNombre(request.nombre());
    curso.setCategoria(request.categoria());
    return toResponse(curso);
  }

  @Transactional
  public void eliminarCurso(Long id) {
    if (!cursoRepository.existsById(id)) {
      throw new EntityNotFoundException("Curso no encontrado");
    }
    cursoRepository.deleteById(id);
  }

  private CursoResponse toResponse(Curso c) {
    return new CursoResponse(c.getId(), c.getNombre(), c.getCategoria());
  }
}
