package com.proyecto.ppi.repository;

import com.proyecto.ppi.entity.Asesoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsesoriaRepository extends JpaRepository<Asesoria, Long> {

}

