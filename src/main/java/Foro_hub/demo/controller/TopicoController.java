package Foro_hub.demo.controller;

import Foro_hub.demo.dto.*;
import Foro_hub.demo.model.Curso;
import Foro_hub.demo.model.Topico;
import Foro_hub.demo.model.Usuario;
import Foro_hub.demo.repository.CursoRepository;
import Foro_hub.demo.repository.TopicoRepository;
import Foro_hub.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Crear un tópico
    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleTopico> crear(@RequestBody @Valid DatosRegistroTopico datos) {
        Usuario usuario = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Curso curso = cursoRepository.findByNombreIgnoreCase(datos.nombreCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Verificar duplicados
        boolean existeDuplicado = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (existeDuplicado) {
            throw new RuntimeException("Ya existe un tópico con ese título y mensaje");
        }

        var topico = new Topico(datos, usuario, curso);
        topicoRepository.save(topico);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    // Listar tópicos filtrando por curso y año
    @GetMapping("/buscar")
    public Page<Topico> buscarPorCursoYAño(
            @RequestParam String curso,
            @RequestParam int anio,
            Pageable pageable) {

        LocalDateTime inicio = LocalDateTime.of(anio, Month.JANUARY, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(anio, Month.DECEMBER, 31, 23, 59);

        return topicoRepository.findByCurso_NombreAndFechaCreacionBetween(curso, inicio, fin, pageable);
    }


    @GetMapping
    public Page<DatosListadoTopico> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
        return topicoRepository.findAll(pageable)
                .map(DatosListadoTopico::new);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        // Actualizar campos
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());

        if (datos.estado() != null) {
            topico.setStatus(datos.estado());
        }

        if (datos.nombreCurso() != null) {
            Curso curso = cursoRepository.findByNombreIgnoreCase(datos.nombreCurso())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            topico.setCurso(curso);
        }

        // Guardar cambios
        topicoRepository.save(topico);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }





}
