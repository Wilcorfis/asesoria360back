package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter



@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;

    private String primer_nombre;
    private String segundo_nmbre;
    private String primer_apellido;
    private String segundo_apellido;
    private String rol;
    private String codigotutor;
    private String correo;
    private String sexo;
    private int telefono;
    private Date fecha_nacimiento;
    private String descripcion;

    // Getters and Setters
}

