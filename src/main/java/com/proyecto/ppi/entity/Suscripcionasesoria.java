package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter




@Entity
@Table(name = "suscripcionasesoria")
public class Suscripcionasesoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_suscripcion;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "fk_id_estudiante", referencedColumnName = "id_usuario")
    private Usuario estudiante;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "fk_id_asesoria", referencedColumnName = "id_asesoria")
    private Asesoria asesoria;
}

// Getters and Setters
