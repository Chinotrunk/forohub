package com.davidlima.forohub.controller.respuesta.dto;

import java.time.LocalDateTime;

public record RespuestaResponse(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        Long topicoId,
        Boolean solucion
) {}
