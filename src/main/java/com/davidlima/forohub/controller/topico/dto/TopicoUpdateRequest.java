package com.davidlima.forohub.controller.topico.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoUpdateRequest(
        @NotBlank String titulo,
        @NotBlank String mensaje
) {}
