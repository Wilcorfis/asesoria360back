package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.Usuario;
import com.proyecto.ppi.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RequestMapping("/usuarios")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los tutores
    @GetMapping("/tutores")
    public List<Usuario> obtenerTutores() {
        return usuarioService.obtenerTutores();
    }
    @PostMapping("/validarCorreo")
    public boolean validarCorreo(@RequestBody String correo) {
        // Validación del dominio de correo
        
     
        if (correo.endsWith("@elpoli.edu.co") ) {
            return usuarioService.verificarCorreo(correo);
           
        }
        return false;
    }
        // Crear nuevo usuario con validación
    @PostMapping
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuario.getCorreo().endsWith("@elpoli.edu.co") ) {
        boolean existe=usuarioService.verificarCorreo(usuario.getCorreo());
        if(existe){
            throw new IllegalArgumentException("Usuario ya registrado con este correo");

        }else{
            return usuarioService.saveUsuario(usuario);
        }

        }else{
            throw new IllegalArgumentException("El correo no pertenece al poli");        
        }
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }



    // Actualizar usuario existente con validación
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioDetails) {
        return usuarioService.updateUsuario(id, usuarioDetails);
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
}


