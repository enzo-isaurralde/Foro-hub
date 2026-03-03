package Foro_hub.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "curso")
@Table(name = "cursos")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoria;
}
