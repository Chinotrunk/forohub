package com.davidlima.forohub.controller.respuesta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespuestaRequest(
        @NotBlank String mensaje,
        @NotNull Long autorId,
        @NotNull Long topicoId,
        Boolean solucion
) {}
