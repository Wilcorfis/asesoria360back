package com.proyecto.ppi.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;


@Getter
@Setter




@Entity
@Table(name = "notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_notificacion;

    private String tipo_notificacion;
    private String mensaje;
    private Date fecha_envio;
    private String estado;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;



    // Getters and Setters
}

