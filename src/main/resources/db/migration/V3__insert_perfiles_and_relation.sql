-- Insertar perfil por defecto
INSERT INTO perfiles (nombre) VALUES ('ROLE_USER');

-- Relacionar el usuario existente (id=1, David) con el perfil ROLE_USER
INSERT INTO usuarios_perfiles (usuario_id, perfil_id)
VALUES (
        1,  -- ID del usuario David
        (SELECT id FROM perfiles WHERE nombre = 'ROLE_USER')
    );