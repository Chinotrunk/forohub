package com.davidlima.forohub.controller;

import com.davidlima.forohub.controller.topico.dto.auth.dto.LoginRequest;
import com.davidlima.forohub.controller.topico.dto.auth.dto.LoginResponse;
import com.davidlima.forohub.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
    String token = authService.login(request);
    return ResponseEntity.ok(new LoginResponse(token));
  }
}
