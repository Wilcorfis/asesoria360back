package com.proyecto.ppi.repository;

import  com.proyecto.ppi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar todos los tutores
    @Query("SELECT u FROM Usuario u WHERE u.rol = 'tutor'")
    List<Usuario> findAllTutores();

    Usuario findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}

