package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.Retroalimentacion;
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
@Controller
public class RetroalimentacionController {

    @Autowired
    private RetroalimentacionService retroalimentacionService;




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
        return retroalimentacionService.updateRetroalimentacion(id, retroalimentacionDetails);
    }

    // Eliminar retroalimentación por ID
    @DeleteMapping("/{id}")
    public void deleteRetroalimentacion(@PathVariable Long id) {
        retroalimentacionService.deleteRetroalimentacion(id);
    }
}

