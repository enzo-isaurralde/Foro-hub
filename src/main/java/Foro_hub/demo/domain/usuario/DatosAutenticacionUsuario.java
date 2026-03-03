package Foro_hub.demo.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank String usuario,
        @NotBlank String contrasena
) {}
