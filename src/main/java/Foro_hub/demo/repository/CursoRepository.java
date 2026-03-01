package Foro_hub.demo.repository;

import Foro_hub.demo.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByCategoria(String categoria);
}
