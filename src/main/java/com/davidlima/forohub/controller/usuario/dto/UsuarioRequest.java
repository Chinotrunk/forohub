package com.davidlima.forohub.controller.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank String nombre,
        @Email @NotBlank String correoElectronico,
        @NotBlank String contrasena
) {}
