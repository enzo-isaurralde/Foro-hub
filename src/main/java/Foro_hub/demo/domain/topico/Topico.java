package Foro_hub.demo.domain.topico;

import Foro_hub.demo.domain.usuario.Usuario;
import Foro_hub.demo.model.Curso;
import Foro_hub.demo.model.Respuesta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "topico")
@Table(name = "topicos")
@Getter @Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor  // ← Usa la anotación de Lombok en lugar de constructor manual
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)  // ← Agrega LAZY para mejor performance
    @JoinColumn(name = "autor_id", nullable = false)  // ← Especifica el nombre exacto de la columna
    private Usuario autor;  // ← Cambiado de "autor_id" a "autor"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;  // ← Cambiado de "curso_id" a "curso"

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    // Constructor que recibe el DTO
    public Topico(DatosRegistroTopico datos, Usuario usuario, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = usuario;      // ← Actualizado
        this.curso = curso;        // ← Actualizado
        this.fechaCreacion = LocalDateTime.now();
        this.status = "ABIERTO";
    }

}