package com.proyecto.ppi.service;

import com.proyecto.ppi.entity.*;
import com.proyecto.ppi.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RetroalimentacionService {

    @Autowired
    private RetroalimentacionRepository retroalimentacionRepository;


    @Autowired
    private UsuarioRepository usuarioRepository; // Para estudiantes y tutores
    @Autowired
    private AsesoriaRepository asesoriaRepository;



    public List<Retroalimentacion> getAllRetroalimentaciones() {
        return retroalimentacionRepository.findAll();
    }

    public Retroalimentacion getRetroalimentacionById(Long id) {
        return retroalimentacionRepository.findById(id).orElse(null);
    }

    public Retroalimentacion saveRetroalimentacion(Retroalimentacion retroalimentacion) {
        return retroalimentacionRepository.save(retroalimentacion);
    }
    public Retroalimentacion updateRetroalimentacion(Long id, Retroalimentacion retroalimentacionDetails) {
        return retroalimentacionRepository.findById(id).map(retroalimentacion -> {

            // Actualizar los campos simples
            retroalimentacion.setPuntaje(retroalimentacionDetails.getPuntaje());
            retroalimentacion.setComentarios(retroalimentacionDetails.getComentarios());
            retroalimentacion.setFecha_retroalimentacion(retroalimentacionDetails.getFecha_retroalimentacion());

            // Actualizar las claves foráneas (asumiendo que las entidades relacionadas ya existen)

            // Actualizar Estudiante
            Optional<Usuario> estudiante = usuarioRepository.findById(retroalimentacionDetails.getEstudiante().getId_usuario());
            if (estudiante.isPresent() && estudiante.get().getRol().equals("estudiante")) {
                retroalimentacion.setEstudiante(estudiante.get());
            } else {
                throw new RuntimeException("Estudiante no encontrado o el usuario no es un estudiante");
            }

            // Actualizar Tutor
            Optional<Usuario> tutor = usuarioRepository.findById(retroalimentacionDetails.getTutor().getId_usuario());
            if (tutor.isPresent() && tutor.get().getRol().equals("tutor")) {
                retroalimentacion.setTutor(tutor.get());
            } else {
                throw new RuntimeException("Tutor no encontrado o el usuario no es un tutor");
            }

            // Actualizar Asesoria
            Optional<Asesoria> asesoria = asesoriaRepository.findById(retroalimentacionDetails.getAsesoria().getId_asesoria());
            if (asesoria.isPresent()) {
                retroalimentacion.setAsesoria(asesoria.get());
            } else {
                throw new RuntimeException("Asesoría no encontrada");
            }

            // Guardar la retroalimentación actualizada
            return retroalimentacionRepository.save(retroalimentacion);
        }).orElseThrow(() -> new RuntimeException("Retroalimentación no encontrada"));
    }



    public void deleteRetroalimentacion(Long id) {
        retroalimentacionRepository.deleteById(id);
    }
}
