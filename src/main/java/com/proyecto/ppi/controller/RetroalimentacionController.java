package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.*;
import com.proyecto.ppi.repository.AsesoriaRepository;
import com.proyecto.ppi.repository.AsignaturaRepository;
import com.proyecto.ppi.repository.HorarioRepository;
import com.proyecto.ppi.repository.UsuarioRepository;
import com.proyecto.ppi.service.RetroalimentacionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RequestMapping("/retroalimentaciones")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RetroalimentacionController {

    @Autowired
    private RetroalimentacionService retroalimentacionService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AsesoriaRepository asesoriaRepository;

    @GetMapping("retroalimentaionporidusuario/{id}")
    public List<Retroalimentacion> obtenerRetroalimentacionPorid(@PathVariable Long id) {
        return retroalimentacionService.obtenerRetroalimentacionPorid(id);
    }
    @GetMapping("retroalimentaionporidusuario2/{id}")
    public Retroalimentacion obtenerRetroalimentacionPorid2(@PathVariable Long id) {
        return retroalimentacionService.obtenerRetroalimentacionPorid2(id);
    }
    @GetMapping("retroalimentaionporidtutor/{idTutor}")
    public List<Retroalimentacion> getRetroalimentacionPorTutor(@PathVariable Long idTutor) {
        return retroalimentacionService.getRetroalimentacionByTutor(idTutor);
    }

    // Obtener todas las retroalimentaciones
    @GetMapping
    public List<Retroalimentacion> getAllRetroalimentaciones() {
        return retroalimentacionService.getAllRetroalimentaciones();
    }

    // Obtener retroalimentación por ID
    @GetMapping("/{id}")
    public Retroalimentacion getRetroalimentacionById(@PathVariable long id) {
        return retroalimentacionService.getRetroalimentacionById(id);
    }

    // Crear nueva retroalimentación con validación
    @PostMapping
    public Retroalimentacion createRetroalimentacion(@Valid @RequestBody Retroalimentacion retroalimentacion) {
        return retroalimentacionService.saveRetroalimentacion(retroalimentacion);
    }

    // Actualizar retroalimentación existente con validación
    @PutMapping("/{id}")
    public Retroalimentacion updateRetroalimentacion(@PathVariable Long id, @Valid @RequestBody Retroalimentacion retroalimentacionDetails) {

        /*Asesoria asesoria= asesoriaRepository.findById(retroalimentacionDetails.getAsesoria().getId_asesoria())
                .orElseThrow(()->new IllegalArgumentException("asesoria no encontrado"));
        Usuario usuario= usuarioRepository.findById(retroalimentacionDetails.getEstudiante().getId_usuario())
                .orElseThrow(()->new IllegalArgumentException("usuario no encontrado"));
        retroalimentacionDetails.setEstudiante(usuario);
        retroalimentacionDetails.setAsesoria(asesoria);*/


        return retroalimentacionService.updateRetroalimentacion(id, retroalimentacionDetails);
    }

    @DeleteMapping("/eliminarPorAsesoria/{fkIdAsesoria}")
    public void eliminarPorFkIdAsesoria(@PathVariable Long fkIdAsesoria) {
        retroalimentacionService.eliminarPorFkIdAsesoria(fkIdAsesoria);
        //return ResponseEntity.noContent().build();
    }

    // Eliminar retroalimentación por ID
    @DeleteMapping("/{id}")
    public void deleteRetroalimentacion(@PathVariable Long id) {
        retroalimentacionService.deleteRetroalimentacion(id);
    }
}

