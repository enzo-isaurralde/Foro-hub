package Foro_hub.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        String estado,
        String nombreCurso
) {}
