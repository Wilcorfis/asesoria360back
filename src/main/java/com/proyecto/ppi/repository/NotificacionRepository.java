package com.proyecto.ppi.repository;

import com.proyecto.ppi.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    @Query("SELECT n FROM Notificacion n WHERE n.usuario.id_usuario = :idUsuario")
    List<Notificacion> findByUsuarioIdUsuario(@Param("idUsuario") Long idUsuario);
}

