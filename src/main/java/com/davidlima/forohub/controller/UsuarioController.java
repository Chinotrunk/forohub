package com.davidlima.forohub.controller;

import com.davidlima.forohub.controller.usuario.dto.UsuarioRequest;
import com.davidlima.forohub.controller.usuario.dto.UsuarioResponse;
import com.davidlima.forohub.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<UsuarioResponse> crear(@RequestBody @Valid UsuarioRequest request) {
    UsuarioResponse response = usuarioService.crearUsuario(request);
    return ResponseEntity.status(201).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<UsuarioResponse>> listar(
          @PageableDefault(size = 10, sort = "nombre") Pageable pageable) {
    return ResponseEntity.ok(usuarioService.listarUsuarios(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResponse> detalle(@PathVariable Long id) {
    return ResponseEntity.ok(usuarioService.obtenerPorId(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    usuarioService.eliminarUsuario(id);
    return ResponseEntity.noContent().build();
  }
}
