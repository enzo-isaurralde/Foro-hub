package Foro_hub.demo.dto;

import Foro_hub.demo.model.Topico;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre());
    }
}
