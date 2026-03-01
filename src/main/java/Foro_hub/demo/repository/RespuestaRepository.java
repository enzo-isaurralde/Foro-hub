package Foro_hub.demo.repository;

import Foro_hub.demo.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByTopicoId(Long topicoId);
    List<Respuesta> findByAutorId(Long autorId);
}
