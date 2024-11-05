package com.proyecto.ppi.repository;

import com.proyecto.ppi.entity.Asesoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsesoriaRepository extends JpaRepository<Asesoria, Long> {
    @Query("SELECT a FROM Asesoria a JOIN Suscripcionasesoria s ON a.id_asesoria = s.asesoria.id_asesoria WHERE s.estudiante.id_usuario = :idEstudiante")
    List<Asesoria> findAsesoriasByEstudianteId(@Param("idEstudiante") Long idEstudiante);

    @Query("SELECT a FROM Asesoria  a WHERE a.tutor.id_usuario =:idTutor")
    List<Asesoria> findAsesoriasByTutorId(@Param("idTutor") Long idTutor);

    @Modifying
    @Transactional
    @Query("DELETE FROM Asesoria a WHERE a.id_asesoria = :idAsesoria")
    void deleteByIdAsesoria(Long idAsesoria);
}

