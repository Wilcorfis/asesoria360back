package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.Asignatura;
import com.proyecto.ppi.service.AsignaturaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RequestMapping("/asignaturas")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    // Obtener todas las asignaturas
    @GetMapping
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaService.getAllAsignaturas();
    }

    // Obtener asignatura por ID
    @GetMapping("/{id}")
    public Asignatura getAsignaturaById(@PathVariable Long id) {
        return asignaturaService.getAsignaturaById(id);
    }

    // Crear nueva asignatura con validación
    @PostMapping
    public Asignatura createAsignatura(@Valid @RequestBody Asignatura asignatura) {
        return asignaturaService.saveAsignatura(asignatura);
    }

    // Actualizar asignatura existente con validación
    @PutMapping("/{id}")
    public Asignatura updateAsignatura(@PathVariable Long id, @Valid @RequestBody Asignatura asignaturaDetails) {
        return asignaturaService.updateAsignatura(id, asignaturaDetails);
    }

    // Eliminar asignatura por ID
    @DeleteMapping("/{id}")
    public void deleteAsignatura(@PathVariable Long id) {
        asignaturaService.deleteAsignatura(id);
    }
}

