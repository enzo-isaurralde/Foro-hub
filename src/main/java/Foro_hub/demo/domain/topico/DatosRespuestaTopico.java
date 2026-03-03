package Foro_hub.demo.domain.topico;

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
                topico.getCurso().getNombre() // ✅ aquí accedés al nombre del curso
        );
    }
}
