package com.proyecto.ppi.controller;
import com.proyecto.ppi.entity.Notificacion;
import com.proyecto.ppi.service.NotificacionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RequestMapping("/notificaciones")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificacionController {

    @Autowired
    private NotificacionService NotificacionService;

    // Obtener todas las Notificaciones
    @GetMapping
    public List<Notificacion> getAllNotificaciones() {
        return NotificacionService.getAllNotificaciones();
    }

    // Obtener notificacion por ID
    @GetMapping("/{id}")
    public Notificacion getNotificacionById(@PathVariable Long id) {
        return NotificacionService.getNotificacionById(id);
    }

    // Crear nueva notificacion con validación
    @PostMapping
    public Notificacion createNotificacion(@Valid @RequestBody Notificacion Notificacion) {
        return NotificacionService.saveNotificacion(Notificacion);
    }

    // Actualizar notificacion existente con validación
    @PutMapping("/{id}")
    public Notificacion updateNotificacion(@PathVariable Long id, @Valid @RequestBody Notificacion NotificacionDetails) {
        return NotificacionService.updateNotificacion(id, NotificacionDetails);
    }

    // Eliminar notificacion por ID
    @DeleteMapping("/{id}")
    public void deleteNotificacion(@PathVariable Long id) {
        NotificacionService.deleteNotificacion(id);
    }
}