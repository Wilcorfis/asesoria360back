package com.proyecto.ppi.service;

import com.proyecto.ppi.entity.Asesoria;
import com.proyecto.ppi.entity.Asignatura;
import com.proyecto.ppi.entity.Horario;
import com.proyecto.ppi.entity.Usuario;
import com.proyecto.ppi.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AsesoriaService {

    @Autowired
    private AsesoriaRepository asesoriaRepository;


    @Autowired
    private RetroalimentacionRepository retroalimentacionRepository;
    @Autowired
    private SuscripcionasesoriaRepository suscripcionasesoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Para acceder a estudiantes y tutores
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<Asesoria> listarAsesoriasPorEstudiante(Long idEstudiante) {
        return asesoriaRepository.findAsesoriasByEstudianteId(idEstudiante);
    }

    public List<Asesoria> listarAsesoriasPorTutor(Long idTutor) {
        return asesoriaRepository.findAsesoriasByTutorId(idTutor);
    }

    public List<Asesoria> getAllAsesorias() {
        return asesoriaRepository.findAll();
    }

    public Asesoria getAsesoriaById(Long id) {
        return asesoriaRepository.findById(id).orElse(null);
    }

    public Asesoria saveAsesoria(Asesoria asesoria) {
        return asesoriaRepository.save(asesoria);
    }

    public Asesoria updateAsesoria(Long id, Asesoria asesoriaDetails) {
        return asesoriaRepository.findById(id).map(asesoria -> {

            // Actualizar los campos simples
            asesoria.setFecha_creacion(asesoriaDetails.getFecha_creacion());
            asesoria.setFecha_asesoria(asesoriaDetails.getFecha_asesoria());
            asesoria.setUbicacion(asesoriaDetails.getUbicacion());
            asesoria.setEstado(asesoriaDetails.getEstado());
            asesoria.setVisibilidad(asesoriaDetails.getVisibilidad());
            asesoria.setCapacidad(asesoriaDetails.getCapacidad());

            // Actualizar las claves foráneas (asumiendo que las entidades relacionadas ya existen)

            // Actualizar Horario
            Optional<Horario> horario = horarioRepository.findById(asesoriaDetails.getHorario().getId_horario());
            if (horario.isPresent()) {
                asesoria.setHorario(horario.get());
            } else {
                throw new RuntimeException("Horario no encontrado");
            }



            // Actualizar Tutor
            Optional<Usuario> tutor = usuarioRepository.findById(asesoriaDetails.getTutor().getId_usuario());
            if (tutor.isPresent() && tutor.get().getRol().equals("Tutor")) {
                asesoria.setTutor(tutor.get());
            } else {
                throw new RuntimeException("Tutor no encontrado o el usuario no es un tutor");
            }

            // Actualizar Asignatura
            Optional<Asignatura> asignatura = asignaturaRepository.findById(asesoriaDetails.getAsignatura().getId_asignatura());
            if (asignatura.isPresent()) {
                asesoria.setAsignatura(asignatura.get());
            } else {
                throw new RuntimeException("Asignatura no encontrada");
            }

            // Guardar la asesoría actualizada
            return asesoriaRepository.save(asesoria);
        }).orElseThrow(() -> new RuntimeException("Asesoría no encontrada"));
    }

    public void eliminarRetroalimentacionYAsesoria(Long fkIdAsesoria) {
        // Primero elimina los registros de retroalimentacion asociados a fkIdAsesoria
        retroalimentacionRepository.deleteByFkIdAsesoria(fkIdAsesoria);
        suscripcionasesoriaRepository.deleteByIdAsesoria(fkIdAsesoria);
        // Luego elimina el registro en la tabla asesoria
        asesoriaRepository.deleteByIdAsesoria(fkIdAsesoria);
    }
}

