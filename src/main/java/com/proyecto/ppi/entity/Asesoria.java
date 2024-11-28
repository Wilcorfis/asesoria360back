package com.proyecto.ppi.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter



@Entity
@Table(name = "asesoria")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @NotNull(message = "La fecha de creación no puede ser nula")
    private Date fecha_creacion;
    @NotNull(message = "La fecha de asesoria no puede ser nula")
    private Date fecha_asesoria;
    @NotBlank(message = "La ubicación no puede estar vacía")
    private String ubicacion;
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
    @NotBlank(message = "La visibilidad no puede estar vacía")
    @Pattern(regexp = "^(publico|privado)$", message = "La visibilidad debe ser publico o privado")
    private String visibilidad;
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    @Max(value = 100, message = "La capacidad no puede superar los 100")
    @NotNull(message = " no puede ser nula")
    private int capacidad;

}

