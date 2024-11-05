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
public class SuscripcionasesoriaService {

    @Autowired
    private SuscripcionasesoriaRepository suscripcionasesoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Para la clave foránea fk_id_usuario
    @Autowired
    private AsesoriaRepository asesoriaRepository; // Para la clave foránea fk_id_asesoria

    public List<Suscripcionasesoria> getAllSuscripcionasesoria() {
        return suscripcionasesoriaRepository.findAll();
    }

    public Suscripcionasesoria  obtenerSuscripcionasesoriaPorid(Long id) {
        return suscripcionasesoriaRepository.findByEstudianteIdUsuario(id);
    }

    public Suscripcionasesoria getSuscripcionasesoriaById(Long id) {
        return suscripcionasesoriaRepository.findById(id).orElse(null);
    }

    public Suscripcionasesoria saveSuscripcionasesoria
            (Suscripcionasesoria suscripcionasesoria) {
        return suscripcionasesoriaRepository.save(suscripcionasesoria);
    }
    public Suscripcionasesoria updateSuscripcionasesoria(Long id, Suscripcionasesoria suscripcionasesoriaDetails) {
        return suscripcionasesoriaRepository.findById(id).map(suscripcionasesoria -> {


            // Actualizar claves foráneas

            // Actualizar Usuarioestudiante (fk_id_usuario)
            Optional<Usuario> usuario = usuarioRepository.findById(suscripcionasesoriaDetails.getEstudiante().getId_usuario());
            if (usuario.isPresent()) {
                suscripcionasesoria.setEstudiante(usuario.get());
            } else {
                throw new RuntimeException("Usuario no encontrado");
            }

            // Actualizar Asesoria (fk_id_asesoria)
            Optional<Asesoria> asesoria = asesoriaRepository.findById(suscripcionasesoriaDetails.getAsesoria().getId_asesoria());
            if (asesoria.isPresent()) {
                suscripcionasesoria.setAsesoria(asesoria.get());
            } else {
                throw new RuntimeException("Asesoría no encontrada");
            }

            // Guardar la actualizada
            return suscripcionasesoriaRepository.save(suscripcionasesoria);
        }).orElseThrow(() -> new RuntimeException("suscripcionasesoria no encontrada"));
    }



    public void deleteSuscripcionasesoria(Long id) {
        suscripcionasesoriaRepository.deleteById(id);
    }
    public Long contarEstudiantesPorAsesoria(Long asesoriaId) {
        return suscripcionasesoriaRepository.countByAsesoriaId(asesoriaId);
    }
}
