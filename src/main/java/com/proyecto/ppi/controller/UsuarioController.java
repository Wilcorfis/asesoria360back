package com.proyecto.ppi.controller;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;

import com.proyecto.ppi.entity.Usuario;
import com.proyecto.ppi.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;


@RequestMapping("/usuarios")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
    private final String SECRET_KEY = "abcdefghijklmnopqrstuvwxy1234567890abcdefghijklmnopqrstuv"; // Mínimo 256 bits
    private final SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");




    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("usuarioporcorreo/{correo}")
    public Usuario obtenerUsuarioPorCorreo(@PathVariable String correo) {
        return usuarioService.obtenerUsuarioPorCorreo(correo);
    }

    // Obtener todos los tutores
    @GetMapping("/tutores")
    public List<Usuario> obtenerTutores() {
        return usuarioService.obtenerTutores();
    }

    @PostMapping("/validarCorreo")
    public boolean validarCorreo(@RequestBody String correo) {
        // Validación del dominio de correo
        
     
        //if (correo.endsWith("@elpoli.edu.co") ) {
            return usuarioService.verificarCorreo(correo);
           
        //}
        //return false;
    }

    private final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos
      // El email como el subject (el "subject" es el campo de usuario)

    @GetMapping(value ="/generartoken/{email}")//, produces = MediaType.TEXT_PLAIN_VALUE
    public String validateEmail(@PathVariable String email) {
        // Validar el email (puedes agregar lógica adicional si es necesario)
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }




        // Generar el token JWT
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date()) // Fecha de creación del token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiración
                .signWith(secretKey, SignatureAlgorithm.HS256) // Firma con la clave secreta
                .compact();

        return token;
    }
        // Crear nuevo usuario con validación
    @PostMapping
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        //if (usuario.getCorreo().endsWith("@elpoli.edu.co") ) {
        boolean existe=usuarioService.verificarCorreo(usuario.getCorreo());
        if(existe){
            throw new IllegalArgumentException("Usuario ya registrado con este correo");

        }else{
            return usuarioService.saveUsuario(usuario);
        }

        //}else{
          //  throw new IllegalArgumentException("El correo no pertenece al poli");
       // }
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


