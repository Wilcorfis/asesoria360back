package com.proyecto.ppi.service;


import com.proyecto.ppi.entity.Usuario;
import com.proyecto.ppi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Implementación del método loadUserByUsername()




    public List<Usuario> obtenerTutores() {
        return usuarioRepository.findAllTutores();
    }
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
    public boolean verificarCorreo(String correo) {
        // Lógica para verificar si el correo pertenece a un usuario registrado en la base de datos
        return usuarioRepository.existsByCorreo(correo);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setPrimer_nombre(usuarioDetails.getPrimer_nombre());
            usuario.setSegundo_nmbre(usuarioDetails.getSegundo_nmbre());
            usuario.setPrimer_apellido(usuarioDetails.getPrimer_apellido());
            usuario.setSegundo_apellido(usuarioDetails.getSegundo_apellido());
            usuario.setRol(usuarioDetails.getRol());
            usuario.setCodigotutor(usuarioDetails.getCodigotutor());
            usuario.setCorreo(usuarioDetails.getCorreo());

            usuario.setSexo(usuarioDetails.getSexo());
            usuario.setTelefono(usuarioDetails.getTelefono());
            usuario.setFecha_nacimiento(usuarioDetails.getFecha_nacimiento());
            usuario.setDescripcion(usuarioDetails.getDescripcion());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}

