package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter




@Entity
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_horario;

    private java.sql.Time hora_inicio;
    private java.sql.Time hora_fin;

    // Getters and Setters
}

