package com.proyecto.ppi.service;

import com.proyecto.ppi.entity.Asignatura;
import com.proyecto.ppi.repository.AsignaturaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;


    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Asignatura getAsignaturaById(Long id) {
        return asignaturaRepository.findById(id).orElse(null);
    }

    public Asignatura saveAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public Asignatura updateAsignatura(Long id, Asignatura asignaturaDetails) {
        return asignaturaRepository.findById(id).map(asignatura -> {
            asignatura.setNombre(asignaturaDetails.getNombre());
            asignatura.setOrganizacion(asignaturaDetails.getOrganizacion());
            return asignaturaRepository.save(asignatura);
        }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    public void deleteAsignatura(Long id) {
        asignaturaRepository.deleteById(id);
    }
}
