package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "La hora de inicio no puede ser nula")
    private java.sql.Time hora_inicio;
    @NotNull(message = "La hora de fin no puede ser nula")
    @FutureOrPresent(message = "La hora de fin debe ser igual o posterior a la hora actual")
    private java.sql.Time hora_fin;

    // Getters and Setters
}

