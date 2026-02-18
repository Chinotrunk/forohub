package com.davidlima.forohub.controller;

import com.davidlima.forohub.controller.curso.dto.CursoRequest;
import com.davidlima.forohub.controller.curso.dto.CursoResponse;
import com.davidlima.forohub.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

  private final CursoService cursoService;

  @PostMapping
  public ResponseEntity<CursoResponse> crear(@RequestBody @Valid CursoRequest request) {
    CursoResponse response = cursoService.crearCurso(request);
    return ResponseEntity.status(201).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<CursoResponse>> listar(
          @PageableDefault(size = 10, sort = "nombre") Pageable pageable) {
    return ResponseEntity.ok(cursoService.listarCursos(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CursoResponse> detalle(@PathVariable Long id) {
    return ResponseEntity.ok(cursoService.obtenerPorId(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CursoResponse> actualizar(
          @PathVariable Long id,
          @RequestBody @Valid CursoRequest request) {
    return ResponseEntity.ok(cursoService.actualizarCurso(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    cursoService.eliminarCurso(id);
    return ResponseEntity.noContent().build();
  }
}
