package com.proyecto.ppi.entity;


import jakarta.persistence.*;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter



@Entity
@Table(name = "asesoria")
public class Asesoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_asesoria;

    // Getters and Setters
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "fk_id_horario", referencedColumnName = "id_horario")
    private Horario horario;


    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "fk_id_usuariotut", referencedColumnName = "id_usuario", nullable=false)
    private Usuario tutor;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "fk_id_asignatura", referencedColumnName = "id_asignatura",nullable=false)
    private Asignatura asignatura;

    private Date fecha_creacion;
    private Date fecha_asesoria;
    private String ubicacion;
    private String estado;
    private String visibilidad;
    private int capacidad;

}

