package Foro_hub.demo.repository;

import Foro_hub.demo.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByCategoria(String categoria);

    //Buscar curso por nombre (Ignorando mayúsculas/minúsculas)
    Optional<Curso> findByNombreIgnoreCase(String nombre);
}
