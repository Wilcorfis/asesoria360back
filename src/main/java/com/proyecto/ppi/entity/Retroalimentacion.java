package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter




@Entity
@Table(name = "retroalimentacion")
public class Retroalimentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_retroalimentacion;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "id_usuario")
    private Usuario estudiante;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "fk_id_asesoria", referencedColumnName = "id_asesoria")
    private Asesoria asesoria;
    private int puntaje;
    private String comentarios;
    private Date fecha_retroalimentacion;

    // Getters and Setters
}

