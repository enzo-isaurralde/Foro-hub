-- V1__init.sql
-- Migración inicial para Foro-Challenge

CREATE TABLE perfiles (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nombre VARCHAR(100) NOT NULL,
                          PRIMARY KEY (id)
);

CREATE TABLE usuarios (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nombre VARCHAR(255) NOT NULL,
                          correoElectronico VARCHAR(100) UNIQUE NOT NULL,
                          contrasena VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id)
);

-- Relación muchos a muchos entre usuarios y perfiles
CREATE TABLE usuario_perfil (
                                usuario_id BIGINT NOT NULL,
                                perfil_id BIGINT NOT NULL,
                                PRIMARY KEY (usuario_id, perfil_id),
                                CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                                CONSTRAINT fk_usuario_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);

CREATE TABLE cursos (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        nombre VARCHAR(255) NOT NULL,
                        categoria VARCHAR(100) NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE topicos (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         titulo VARCHAR(255) NOT NULL,
                         mensaje TEXT NOT NULL,
                         fechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         status VARCHAR(50) NOT NULL,
                         autor_id BIGINT NOT NULL,
                         curso_id BIGINT NOT NULL,
                         PRIMARY KEY (id),
                         CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id),
                         CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

CREATE TABLE respuestas (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            mensaje TEXT NOT NULL,
                            fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            solucion BOOLEAN DEFAULT FALSE,
                            autor_id BIG