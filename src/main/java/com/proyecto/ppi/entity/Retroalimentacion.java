package com.proyecto.ppi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter




@Entity
@Table(name = "retroalimentacion")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Min(value = 1, message = "El puntaje mínimo debe ser 1")
    @Max(value = 5, message = "El puntaje máximo debe ser 5")
    @NotNull(message = "el puntaje no puede ser nula")
    private int puntaje;
    @Size(max = 500, message = "Los comentarios no pueden exceder los 500 caracteres")
    @NotNull(message = "comentarios not debe ser null")
    private String comentarios;
    @NotNull(message = "La fecha de retroalimentación no puede ser nula")
    private Date fecha_retroalimentacion;

    // Getters and Setters
}

