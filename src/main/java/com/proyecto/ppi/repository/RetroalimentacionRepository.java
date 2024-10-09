package com.proyecto.ppi.repository;


import com.proyecto.ppi.entity.Retroalimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetroalimentacionRepository extends JpaRepository<Retroalimentacion, Long> {



}

