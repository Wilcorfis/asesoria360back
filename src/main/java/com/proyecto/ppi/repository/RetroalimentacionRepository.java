package com.proyecto.ppi.repository;


import com.proyecto.ppi.entity.Retroalimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetroalimentacionRepository extends JpaRepository<Retroalimentacion, Long> {
    @Query("SELECT r FROM Retroalimentacion r WHERE r.estudiante.id_usuario = :idUsuario")
    Retroalimentacion findByEstudianteIdUsuario2(@Param("idUsuario") Long idUsuario);

    @Query("SELECT r FROM Retroalimentacion r WHERE r.estudiante.id_usuario = :idUsuario")
    List<Retroalimentacion> findByEstudianteIdUsuario(@Param("idUsuario") Long idUsuario);

}

