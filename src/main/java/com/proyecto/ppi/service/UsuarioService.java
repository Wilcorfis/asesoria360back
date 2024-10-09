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

    public List<Usuario> obtenerTutores() {
        return usuarioRepository.findAllTutores();
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
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setClave(usuarioDetails.getClave());
            usuario.setSexo(usuarioDetails.getSexo());
            usuario.setTelefono(usuarioDetails.getTelefono());
            usuario.setFecha_nacimiento(usuarioDetails.getFecha_nacimiento());
            usuario.setDescripcion(usuarioDetails.getDescripcion());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}

