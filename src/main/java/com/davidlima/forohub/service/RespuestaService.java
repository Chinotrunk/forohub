package com.davidlima.forohub.service;

import com.davidlima.forohub.controller.respuesta.dto.RespuestaRequest;
import com.davidlima.forohub.controller.respuesta.dto.RespuestaResponse;
import com.davidlima.forohub.domain.Respuesta;
import com.davidlima.forohub.domain.Topico;
import com.davidlima.forohub.domain.Usuario;
import com.davidlima.forohub.repository.RespuestaRepository;
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
public class RespuestaService {

  private final RespuestaRepository respuestaRepository;
  private final UsuarioRepository usuarioRepository;
  private final TopicoRepository topicoRepository;

  @Transactional
  public RespuestaResponse crearRespuesta(RespuestaRequest request) {
    Usuario autor = usuarioRepository.findById(request.autorId())
            .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
    Topico topico = topicoRepository.findById(request.topicoId())
            .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

    Respuesta respuesta = Respuesta.builder()
            .mensaje(request.mensaje())
            .autor(autor)
            .topico(topico)
            .fechaCreacion(LocalDateTime.now())
            .solucion(request.solucion() != null ? request.solucion() : false)
            .build();

    Respuesta guardada = respuestaRepository.save(respuesta);
    return toResponse(guardada);
  }

  public Page<RespuestaResponse> listarRespuestas(Pageable pageable) {
    return respuestaRepository.findAll(pageable)
            .map(this::toResponse);
  }

  public Page<RespuestaResponse> listarPorTopico(Long topicoId, Pageable pageable) {
    return respuestaRepository.findByTopicoId(topicoId, pageable)
            .map(this::toResponse);
  }

  public RespuestaResponse obtenerPorId(Long id) {
    Respuesta respuesta = respuestaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada"));
    return toResponse(respuesta);
  }

  @Transactional
  public void eliminarRespuesta(Long id) {
    if (!respuestaRepository.existsById(id)) {
      throw new EntityNotFoundException("Respuesta no encontrada");
    }
    respuestaRepository.deleteById(id);
  }

  private RespuestaResponse toResponse(Respuesta r) {
    return new RespuestaResponse(
            r.getId(),
            r.getMensaje(),
            r.getFechaCreacion(),
            r.getAutor().getNombre(),
            r.getTopico().getId(),
            r.getSolucion()
    );
  }
}
