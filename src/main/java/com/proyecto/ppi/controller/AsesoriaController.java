package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.Asesoria;
import com.proyecto.ppi.entity.Usuario;
import com.proyecto.ppi.entity.Horario;
import com.proyecto.ppi.entity.Asignatura;
import com.proyecto.ppi.repository.AsignaturaRepository;
import com.proyecto.ppi.repository.HorarioRepository;
import com.proyecto.ppi.repository.UsuarioRepository;
import com.proyecto.ppi.service.AsesoriaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/asesorias")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AsesoriaController {

    @Autowired
    private AsesoriaService asesoriaService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @GetMapping("/estudiante/{idEstudiante}")
    public List<Asesoria> getAsesoriasPorEstudiante(@PathVariable Long idEstudiante) {
        return asesoriaService.listarAsesoriasPorEstudiante(idEstudiante);
    }

    // Obtener todas las asesorías
    @GetMapping
    public List<Asesoria> getAllAsesorias() {
        return asesoriaService.getAllAsesorias();
    }

    // Obtener asesoría por ID_asesoria
    @GetMapping("/{id}")
    public Asesoria getAsesoriaById(@PathVariable Long id) {
        return asesoriaService.getAsesoriaById(id);
    }

    // Crear nueva asesoría con validación
    @PostMapping
    public Asesoria createAsesoria(@Valid @RequestBody Asesoria asesoria) {
        Horario horario= horarioRepository.findById(asesoria.getHorario().getId_horario())
                .orElseThrow(()->new IllegalArgumentException("horario no encontrado"));
        Asignatura asignatura= asignaturaRepository.findById(asesoria.getAsignatura().getId_asignatura())
                .orElseThrow(()->new IllegalArgumentException("asignatura no encontrado"));
        Usuario usuario= usuarioRepository.findById(asesoria.getTutor().getId_usuario())
                .orElseThrow(()->new IllegalArgumentException("usuario no encontrado"));
        asesoria.setTutor(usuario);
        asesoria.setAsignatura(asignatura);
        asesoria.setHorario(horario);



        return asesoriaService.saveAsesoria(asesoria);
    }

    // Actualizar asesoría existente con validación
    @PutMapping("/{id}")
    public Asesoria updateAsesoria(@PathVariable Long id, @Valid @RequestBody Asesoria asesoriaDetails) {
        return asesoriaService.updateAsesoria(id, asesoriaDetails);
    }

    // Eliminar asesoría por ID
    @DeleteMapping("/{id}")
    public void deleteAsesoria(@PathVariable Long id) {
        asesoriaService.deleteAsesoria(id);
    }
}

