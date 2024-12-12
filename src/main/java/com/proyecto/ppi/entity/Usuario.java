package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;

import jakarta.validation.constraints.*;
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
    @NotBlank(message = "El primer nombre no puede estar vacío")
    @Size(max = 50, message = "El primer nombre no puede exceder los 50 caracteres")
    private String primer_nombre;
    @Size(max = 50, message = "El segundo nombre no puede exceder los 50 caracteres")
    private String segundo_nmbre;
    @NotBlank(message = "El primer apellido no puede estar vacío")
    @Size(max = 50, message = "El primer apellido no puede exceder los 50 caracteres")
    private String primer_apellido;
    @Size(max = 50, message = "El segundo apellido no puede exceder los 50 caracteres")
    private String segundo_apellido;
    @NotBlank(message = "El rol no puede estar vacío")
    @Pattern(regexp = "^(Estudiante|Tutor)$", message = "El rol debe ser Estudiante, Tutor ")
    private String rol;
    @Size(max = 20, message = "El código de tutor no puede exceder los 20 caracteres")
    private String codigotutor;
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    @Size(max = 100, message = "El correo no puede exceder los 100 caracteres")
    private String correo;
    @NotBlank(message = "El sexo no puede estar vacío")
    @Pattern(regexp = "^(masculino|femenino)$", message = "El sexo debe ser Masculino, Femenino")
    private String sexo;
    @NotNull(message = "El teléfono no puede estar vacío")
    private String telefono;
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private Date fecha_nacimiento;
    @NotBlank(message = " no puede estar vacío")
    private String descripcion;

    // Getters and Setters
}

