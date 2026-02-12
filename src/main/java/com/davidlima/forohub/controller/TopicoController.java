package com.davidlima.forohub.controller;

import com.davidlima.forohub.controller.topico.dto.TopicoRequest;
import com.davidlima.forohub.controller.topico.dto.TopicoResponse;
import com.davidlima.forohub.controller.topico.dto.TopicoUpdateRequest;
import com.davidlima.forohub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

  private final TopicoService topicoService;

  @PostMapping
  public ResponseEntity<TopicoResponse> crear(@RequestBody @Valid TopicoRequest request) {
    TopicoResponse response = topicoService.crearTopico(request);
    return ResponseEntity.status(201).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<TopicoResponse>> listar(
          @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
    return ResponseEntity.ok(topicoService.listarTopicos(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicoResponse> detalle(@PathVariable Long id) {
    return ResponseEntity.ok(topicoService.obtenerPorId(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TopicoResponse> actualizar(
          @PathVariable Long id,
          @RequestBody @Valid TopicoUpdateRequest request) {
    return ResponseEntity.ok(topicoService.actualizarTopico(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    topicoService.eliminarTopico(id);
    return ResponseEntity.noContent().build();
  }
}
