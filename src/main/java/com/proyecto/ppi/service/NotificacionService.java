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
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Para la clave foránea fk_id_usuario
    @Autowired
    private AsesoriaRepository asesoriaRepository; // Para la clave foránea fk_id_asesoria

    public List<Notificacion> getAllNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Notificacion getNotificacionById(Long id) {
        return notificacionRepository.findById(id).orElse(null);
    }

    public Notificacion saveNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }
    public Notificacion updateNotificacion(Long id, Notificacion notificacionDetails) {
        return notificacionRepository.findById(id).map(notificacion -> {

            // Actualizar campos simples
            notificacion.setTipo_notificacion(notificacionDetails.getTipo_notificacion());
            notificacion.setMensaje(notificacionDetails.getMensaje());
            notificacion.setFecha_envio(notificacionDetails.getFecha_envio());
            notificacion.setEstado(notificacionDetails.getEstado());

            // Actualizar claves foráneas

            // Actualizar Usuario (fk_id_usuario)
            Optional<Usuario> usuario = usuarioRepository.findById(notificacionDetails.getUsuario().getId_usuario());
            if (usuario.isPresent()) {
                notificacion.setUsuario(usuario.get());
            } else {
                throw new RuntimeException("Usuario no encontrado");
            }

            // Actualizar Asesoria (fk_id_asesoria)
            Optional<Asesoria> asesoria = asesoriaRepository.findById(notificacionDetails.getAsesoria().getId_asesoria());
            if (asesoria.isPresent()) {
                notificacion.setAsesoria(asesoria.get());
            } else {
                throw new RuntimeException("Asesoría no encontrada");
            }

            // Guardar la notificación actualizada
            return notificacionRepository.save(notificacion);
        }).orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }



    public void deleteNotificacion(Long id) {
        notificacionRepository.deleteById(id);
    }
}
