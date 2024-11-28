package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.*;
import com.proyecto.ppi.repository.AsesoriaRepository;
import com.proyecto.ppi.repository.AsignaturaRepository;
import com.proyecto.ppi.repository.HorarioRepository;
import com.proyecto.ppi.repository.UsuarioRepository;
import com.proyecto.ppi.service.RetroalimentacionService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RequestMapping("/retroalimentaciones")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RetroalimentacionController {
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
    private RetroalimentacionService retroalimentacionService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AsesoriaRepository asesoriaRepository;

    @GetMapping("retroalimentaionporidusuario/{id}")
    public List<Retroalimentacion> obtenerRetroalimentacionPorid(@PathVariable Long id) {
        return retroalimentacionService.obtenerRetroalimentacionPorid(id);
    }
    @GetMapping("retroalimentaionporidusuario2/{id}")
    public Retroalimentacion obtenerRetroalimentacionPorid2(@PathVariable Long id) {
        return retroalimentacionService.obtenerRetroalimentacionPorid2(id);
    }
    @GetMapping("retroalimentaionporidtutor/{idTutor}")
    public List<Retroalimentacion> getRetroalimentacionPorTutor(@PathVariable Long idTutor) {
        return retroalimentacionService.getRetroalimentacionByTutor(idTutor);
    }

    // Obtener todas las retroalimentaciones
    @GetMapping
    public List<Retroalimentacion> getAllRetroalimentaciones() {
        return retroalimentacionService.getAllRetroalimentaciones();
    }

    // Obtener retroalimentación por ID
    @GetMapping("/{id}")
    public Retroalimentacion getRetroalimentacionById(@PathVariable long id) {
        return retroalimentacionService.getRetroalimentacionById(id);
    }

    // Crear nueva retroalimentación con validación
    @PostMapping
    public Retroalimentacion createRetroalimentacion(@Valid @RequestBody Retroalimentacion retroalimentacion, @RequestHeader("Authorization") String authorization) {
        if (retroalimentacion.getEstudiante().getCorreo().equals(validateToken(authorization).getSubject())){
            return retroalimentacionService.saveRetroalimentacion(retroalimentacion);

        }
        throw new IllegalArgumentException("El correo del tutor no coincide con el token");

    }

    // Actualizar retroalimentación existente con validación
    @PutMapping("/{id}")
    public Retroalimentacion updateRetroalimentacion(@PathVariable Long id, @Valid @RequestBody Retroalimentacion retroalimentacionDetails, @RequestHeader("Authorization") String authorization) {

        if (retroalimentacionDetails.getEstudiante().getCorreo().equals(validateToken(authorization).getSubject())){
            return retroalimentacionService.updateRetroalimentacion(id, retroalimentacionDetails);

        }
        throw new IllegalArgumentException("El correo no coincide con el token");



    }

    @DeleteMapping("/eliminarPorAsesoria/{fkIdAsesoria}")
    public void eliminarPorFkIdAsesoria(@PathVariable Long fkIdAsesoria) {

        retroalimentacionService.eliminarPorFkIdAsesoria(fkIdAsesoria);
        //return ResponseEntity.noContent().build();
    }

    // Eliminar retroalimentación por ID
    @DeleteMapping("/{id}")
    public void deleteRetroalimentacion(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
        Retroalimentacion retro= retroalimentacionService.getRetroalimentacionById(id);
        if (retro.getEstudiante().getCorreo().equals(validateToken(authorization).getSubject())){
            retroalimentacionService.deleteRetroalimentacion(id);

        }
        throw new IllegalArgumentException("El correo no coincide con el token");

    }
}

