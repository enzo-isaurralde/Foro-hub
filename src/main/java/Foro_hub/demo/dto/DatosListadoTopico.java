package Foro_hub.demo.dto;

import Foro_hub.demo.model.Topico;
import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String estado,
        LocalDateTime fechaCreacion,
        String autorNombre,
        String cursoNombre
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getFechaCreacion(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
