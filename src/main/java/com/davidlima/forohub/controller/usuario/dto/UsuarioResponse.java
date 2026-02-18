package com.davidlima.forohub.controller.usuario.dto;

import java.util.Set;

public record UsuarioResponse(
        Long id,
        String nombre,
        String correoElectronico,
        Set<String> perfiles
) {}