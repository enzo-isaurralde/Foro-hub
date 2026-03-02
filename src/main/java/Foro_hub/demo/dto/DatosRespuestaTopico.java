package Foro_hub.demo.dto;

import Foro_hub.demo.model.Topico;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String nombreAutor,
        String nombreCurso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
