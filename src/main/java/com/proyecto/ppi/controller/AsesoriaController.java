package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.Asesoria;
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
public class AsesoriaController {

    @Autowired
    private AsesoriaService asesoriaService;



    // Obtener todas las asesorías
    @GetMapping
    public List<Asesoria> getAllAsesorias() {
        return asesoriaService.getAllAsesorias();
    }

    // Obtener asesoría por ID
    @GetMapping("/{id}")
    public Asesoria getAsesoriaById(@PathVariable Long id) {
        return asesoriaService.getAsesoriaById(id);
    }

    // Crear nueva asesoría con validación
    @PostMapping
    public Asesoria createAsesoria(@Valid @RequestBody Asesoria asesoria) {
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

