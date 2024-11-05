package com.proyecto.ppi.repository;

import com.proyecto.ppi.entity.Suscripcionasesoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuscripcionasesoriaRepository extends JpaRepository<Suscripcionasesoria, Long> {
    @Query("SELECT s FROM Suscripcionasesoria s WHERE s.estudiante.id_usuario = :idUsuario")
    Suscripcionasesoria findByEstudianteIdUsuario(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Transactional
    @Query("DELETE FROM Suscripcionasesoria a WHERE a.asesoria.id_asesoria = :idAsesoria")
    void deleteByIdAsesoria(Long idAsesoria);

    @Query("SELECT COUNT(s) FROM Suscripcionasesoria s WHERE s.asesoria.id_asesoria = :asesoriaId")
    Long countByAsesoriaId(@Param("asesoriaId") Long asesoriaId);
}