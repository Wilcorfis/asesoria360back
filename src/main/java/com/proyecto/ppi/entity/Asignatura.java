package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter




@Entity
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_asignatura;
    @NotBlank(message = "no puede estar vacío")
    private String nombre;
    @NotBlank(message = "no puede estar vacío")
    private String organizacion;

    // Getters and Setters
}

