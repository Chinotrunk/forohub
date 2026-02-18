package com.davidlima.forohub.controller.curso.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoRequest(
        @NotBlank String nombre,
        @NotBlank String categoria
) {}
