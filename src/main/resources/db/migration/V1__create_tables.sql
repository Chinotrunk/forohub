CREATE TABLE cursos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        categoria VARCHAR(100) NOT NULL
);

CREATE TABLE perfiles (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL
);

CREATE TABLE usuarios (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        correo_electronico VARCHAR(150) NOT NULL UNIQUE,
                        contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios_perfiles (
                                usuario_id BIGINT NOT NULL,
                                perfil_id BIGINT NOT NULL,
                                PRIMARY KEY (usuario_id, perfil_id),
                                CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                                CONSTRAINT fk_usuario_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);

CREATE TABLE topicos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(200) NOT NULL,
                        mensaje VARCHAR(1000) NOT NULL,
                        fecha_creacion DATETIME NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        autor_id BIGINT NOT NULL,
                        curso_id BIGINT NOT NULL,
                        CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id),
                        CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);


CREATE TABLE respuestas (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            mensaje TEXT NOT NULL,
                            fecha_creacion DATETIME NOT NULL,
                            autor_id BIGINT NOT NULL,
                            topico_id BIGINT NOT NULL,
                            solucion BOOLEAN NOT NULL DEFAULT FALSE,
                            CONSTRAINT fk_respuesta_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id),
                            CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES topicos(id)
);