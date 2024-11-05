package com.proyecto.ppi.controller;
import com.proyecto.ppi.entity.Retroalimentacion;
import com.proyecto.ppi.entity.Suscripcionasesoria;
import com.proyecto.ppi.service.SuscripcionasesoriaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RequestMapping("/suscripcionasesoria")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SuscripcionasesoriaController {

    @Autowired
    private SuscripcionasesoriaService SuscripcionasesoriaService;

    @GetMapping("suscripcionporidusuario/{id}")
    public Suscripcionasesoria obtenerSuscripcionPorid(@PathVariable Long id) {
        return SuscripcionasesoriaService.obtenerSuscripcionasesoriaPorid(id);
    }

    // Obtener todas las Suscripcionasesoriaes
    @GetMapping
    public List<Suscripcionasesoria> getAllSuscripcionasesoriaes() {
        return SuscripcionasesoriaService.getAllSuscripcionasesoria();
    }

    // Obtener  por ID
    @GetMapping("/{id}")
    public Suscripcionasesoria getSuscripcionasesoriaById(@PathVariable Long id) {
        return SuscripcionasesoriaService.getSuscripcionasesoriaById(id);
    }

    // Crear nueva  con validación
    @PostMapping
    public Suscripcionasesoria createSuscripcionasesoria(@Valid @RequestBody Suscripcionasesoria Suscripcionasesoria) {
        return SuscripcionasesoriaService.saveSuscripcionasesoria(Suscripcionasesoria);
    }

    // Actualizar  existente con validación
    @PutMapping("/{id}")
    public Suscripcionasesoria updateSuscripcionasesoria(@PathVariable Long id, @Valid @RequestBody Suscripcionasesoria SuscripcionasesoriaDetails) {
        return SuscripcionasesoriaService.updateSuscripcionasesoria(id, SuscripcionasesoriaDetails);
    }

    // Eliminar  por ID
    @DeleteMapping("/{id}")
    public void deleteSuscripcionasesoria(@PathVariable Long id) {
        SuscripcionasesoriaService.deleteSuscripcionasesoria(id);
    }
    @GetMapping("/contar-estudiantes/{asesoriaId}")
    public Long contarEstudiantesPorAsesoria(@PathVariable Long asesoriaId) {
        return SuscripcionasesoriaService.contarEstudiantesPorAsesoria(asesoriaId);
    }
}
