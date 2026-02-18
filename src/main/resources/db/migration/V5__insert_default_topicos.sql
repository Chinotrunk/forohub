-- Insertar tópicos de ejemplo (autor_id=1 es David, curso_id=1 es Spring Boot)
INSERT INTO topicos (titulo, mensaje, fecha_creacion, status, autor_id, curso_id) VALUES
            ('¿Cómo funciona @Transactional?', 'Tengo dudas sobre el alcance de las transacciones en Spring.', NOW(), 'ABIERTO', 1, 1),
            ('Error al conectar con MySQL', 'Me aparece un error de conexión rechazada.', NOW(), 'ABIERTO', 1, 3),
            ('Mejores prácticas con JWT', '¿Dónde debo guardar el token en el frontend?', NOW(), 'ABIERTO', 1, 1);