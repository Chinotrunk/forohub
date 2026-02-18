package com.davidlima.forohub.controller;

import com.davidlima.forohub.controller.respuesta.dto.RespuestaRequest;
import com.davidlima.forohub.controller.respuesta.dto.RespuestaResponse;
import com.davidlima.forohub.service.RespuestaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
@RequiredArgsConstructor
public class RespuestaController {

  private final RespuestaService respuestaService;

  @PostMapping
  public ResponseEntity<RespuestaResponse> crear(@RequestBody @Valid RespuestaRequest request) {
    RespuestaResponse response = respuestaService.crearRespuesta(request);
    return ResponseEntity.status(201).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<RespuestaResponse>> listar(
          @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
    return ResponseEntity.ok(respuestaService.listarRespuestas(pageable));
  }

  @GetMapping("/topico/{topicoId}")
  public ResponseEntity<Page<RespuestaResponse>> listarPorTopico(
          @PathVariable Long topicoId,
          @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
    return ResponseEntity.ok(respuestaService.listarPorTopico(topicoId, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaResponse> detalle(@PathVariable Long id) {
    return ResponseEntity.ok(respuestaService.obtenerPorId(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    respuestaService.eliminarRespuesta(id);
    return ResponseEntity.noContent().build();
  }
}
