package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;
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

    private String nombre;
    private String organizacion;

    // Getters and Setters
}

