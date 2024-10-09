package com.proyecto.ppi.service;


import com.proyecto.ppi.entity.Horario;
import com.proyecto.ppi.repository.HorarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
@AllArgsConstructor
@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;



    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public Horario getHorarioById(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public Horario saveHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario updateHorario(Long id, Horario horarioDetails) {
        return horarioRepository.findById(id).map(horario -> {

            horario.setHora_inicio(horarioDetails.getHora_inicio());
            horario.setHora_fin(horarioDetails.getHora_fin());
            return horarioRepository.save(horario);
        }).orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }

    public void deleteHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}

