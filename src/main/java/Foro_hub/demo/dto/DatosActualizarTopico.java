package Foro_hub.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        String estado,
        String nombreCurso
) {}
