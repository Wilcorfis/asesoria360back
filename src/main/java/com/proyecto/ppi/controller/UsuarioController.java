package com.proyecto.ppi.controller;
import com.proyecto.ppi.entity.Asesoria;
import io.jsonwebtoken.*;


import io.jsonwebtoken.io.DecodingException;
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
    public Claims validateToken(String token) {
        try {
            // Validar si el token contiene el prefijo "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7); // Remover "Bearer " del token
            }

            // Validar que el token no esté vacío después de eliminar el prefijo
            if (token.isBlank()) {
                throw new IllegalArgumentException("El token está vacío después de eliminar el prefijo Bearer");
            }

            // Decodificar y validar el token
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey) // Clave secreta debe estar configurada correctamente
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Token expirado", e);
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("Formato de token no soportado", e);
        } catch (MalformedJwtException e) {
            throw new IllegalArgumentException("Token mal formado", e);
        } catch (DecodingException e) {
            throw new IllegalArgumentException("Error de decodificación del token", e);
        }
    }



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
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario, @RequestHeader("Authorization") String authorization) {
        //if (usuario.getCorreo().endsWith("@elpoli.edu.co") ) {
        boolean existe=usuarioService.verificarCorreo(usuario.getCorreo());
        if(existe){
            throw new IllegalArgumentException("Usuario ya registrado con este correo");

        }else{
            if (usuario.getCorreo().equals(validateToken(authorization).getSubject())){
                return usuarioService.saveUsuario(usuario);

            }
            throw new IllegalArgumentException("El correo no coincide con el token");

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
    public Usuario updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioDetails, @RequestHeader("Authorization") String authorization) {
        if (usuarioDetails.getCorreo().equals(validateToken(authorization).getSubject())){
            return usuarioService.updateUsuario(id, usuarioDetails);

        }
        throw new IllegalArgumentException("El correo no coincide con el token");

    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
        Usuario usuario= usuarioService.getUsuarioById(id);
        if (usuario.getCorreo().equals(validateToken(authorization).getSubject())){
            usuarioService.deleteUsuario(id);

        }


    }
}


