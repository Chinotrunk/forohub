package com.davidlima.forohub.controller.topico.dto.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email @NotBlank String correoElectronico,
        @NotBlank String contrasena
) {}
