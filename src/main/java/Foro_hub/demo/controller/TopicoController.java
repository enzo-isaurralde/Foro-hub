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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // ============================================
    // POST: Crear tópico
    // ============================================
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

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    // ============================================
    // GET: Listar todos (con paginación)
    // ============================================
    @GetMapping
    public Page<DatosListadoTopico> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
        return topicoRepository.findAll(pageable)
                .map(DatosListadoTopico::new);
    }

    // ============================================
    // GET: Buscar por curso y año
    // ============================================
    @GetMapping("/buscar")
    public Page<DatosListadoTopico> buscarPorCursoYAño(
            @RequestParam String curso,
            @RequestParam int anio,
            Pageable pageable) {

        LocalDateTime inicio = LocalDateTime.of(anio, Month.JANUARY, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(anio, Month.DECEMBER, 31, 23, 59);

        return topicoRepository.findByCurso_NombreAndFechaCreacionBetween(curso, inicio, fin, pageable)
                .map(DatosListadoTopico::new);
    }


    // ============================================
    // GET: Detalle por ID
    // ============================================
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    // ============================================
    // PUT: Actualizar tópico (ÚNICO - por ID en URL)
    // ============================================
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        if (datos.titulo() != null) {
            topico.setTitulo(datos.titulo());
        }
        if (datos.mensaje() != null) {
            topico.setMensaje(datos.mensaje());
        }
        if (datos.estado() != null) {
            topico.setStatus(datos.estado());
        }
        if (datos.nombreCurso() != null) {
            Curso curso = cursoRepository.findByNombreIgnoreCase(datos.nombreCurso())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            topico.setCurso(curso);
        }

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    // ============================================
    // DELETE: Eliminar tópico
    // ============================================
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (!topicoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}