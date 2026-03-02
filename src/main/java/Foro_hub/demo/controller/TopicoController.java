package Foro_hub.demo.controller;

import Foro_hub.demo.dto.DatosDetalleTopico;
import Foro_hub.demo.dto.DatosRegistroTopico;
import Foro_hub.demo.model.Curso;
import Foro_hub.demo.model.Topico;
import Foro_hub.demo.model.Usuario;
import Foro_hub.demo.repository.CursoRepository;
import Foro_hub.demo.repository.TopicoRepository;
import Foro_hub.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

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


}
