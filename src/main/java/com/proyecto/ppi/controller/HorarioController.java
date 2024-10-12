package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.Horario;
import com.proyecto.ppi.service.HorarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalTime;
import java.util.List;


@RequestMapping("/horarios")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;



    // Obtener todos los horarios
    @GetMapping
    public List<Horario> getAllHorarios() {
        return horarioService.getAllHorarios();
    }

    // Obtener horario por ID
    @GetMapping("/{id}")
    public Horario getHorarioById(@PathVariable Long id) {
        return horarioService.getHorarioById(id);
    }

    // Crear nuevo horario con validación
    @PostMapping
    public Horario createHorario(@Valid @RequestBody Horario horario) {
        return horarioService.saveHorario(horario);
    }

    // Actualizar horario existente con validación
    @PutMapping("/{id}")
    public Horario updateHorario(@PathVariable Long id, @Valid @RequestBody Horario horarioDetails) {
        return horarioService.updateHorario(id, horarioDetails);
    }

    // Eliminar horario por ID
    @DeleteMapping("/{id}")
    public void deleteHorario(@PathVariable Long id) {
        horarioService.deleteHorario(id);
    }
}
