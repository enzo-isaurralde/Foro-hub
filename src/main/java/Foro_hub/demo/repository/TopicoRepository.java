package Foro_hub.demo.repository;

import Foro_hub.demo.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByAutorId(Long autorId);
    List<Topico> findByCursoId(Long cursoId);
}
