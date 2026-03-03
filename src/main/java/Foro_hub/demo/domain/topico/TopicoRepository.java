package Foro_hub.demo.domain.topico;

import jakarta.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByAutorId(Long autorId);
    List<Topico> findByCursoId(Long cursoId);

    Page<Topico> findByCursoAndFechaCreacionBetween(
            String curso,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable paginacion
    );

    boolean existsByTituloAndMensaje(@NotBlank String titulo, @NotBlank String mensaje);

    Page<Topico> findByCurso_NombreAndFechaCreacionBetween(
            String nombreCurso,
            LocalDateTime inicio,
            LocalDateTime fin,
            Pageable pageable);
}
