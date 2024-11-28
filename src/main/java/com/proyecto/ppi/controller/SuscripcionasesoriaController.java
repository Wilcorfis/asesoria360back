package com.proyecto.ppi.controller;
import com.proyecto.ppi.entity.Retroalimentacion;
import com.proyecto.ppi.entity.Suscripcionasesoria;
import com.proyecto.ppi.service.SuscripcionasesoriaService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RequestMapping("/suscripcionasesoria")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SuscripcionasesoriaController {
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
    private SuscripcionasesoriaService SuscripcionasesoriaService;

    @GetMapping("suscripcionporidusuario/{id}")
    public List<Suscripcionasesoria> obtenerSuscripcionPorid(@PathVariable Long id) {
        return SuscripcionasesoriaService.obtenerSuscripcionasesoriaPorid(id);
    }

    // Obtener todas las Suscripcionasesoriaes
    @GetMapping
    public List<Suscripcionasesoria> getAllSuscripcionasesoriaes() {
        return SuscripcionasesoriaService.getAllSuscripcionasesoria();
    }

    // Obtener  por ID
    @GetMapping("/{id}")
    public Suscripcionasesoria getSuscripcionasesoriaById(@PathVariable Long id) {
        return SuscripcionasesoriaService.getSuscripcionasesoriaById(id);
    }

    // Crear nueva  con validación
    @PostMapping
    public Suscripcionasesoria createSuscripcionasesoria(@Valid @RequestBody Suscripcionasesoria suscripcionasesoria, @RequestHeader("Authorization") String authorization) {
        if (suscripcionasesoria.getEstudiante().getCorreo().equals(validateToken(authorization).getSubject())){
            return SuscripcionasesoriaService.saveSuscripcionasesoria(suscripcionasesoria);

        }
        throw new IllegalArgumentException("El correo  no coincide con el token");

    }

    // Actualizar  existente con validación
    @PutMapping("/{id}")
    public Suscripcionasesoria updateSuscripcionasesoria(@PathVariable Long id, @Valid @RequestBody Suscripcionasesoria SuscripcionasesoriaDetails) {
        return SuscripcionasesoriaService.updateSuscripcionasesoria(id, SuscripcionasesoriaDetails);
    }

    // Eliminar  por ID
    @DeleteMapping("/{id}")
    public void deleteSuscripcionasesoria(@PathVariable Long id) {
        SuscripcionasesoriaService.deleteSuscripcionasesoria(id);
    }
    @GetMapping("/contar-estudiantes/{asesoriaId}")
    public Long contarEstudiantesPorAsesoria(@PathVariable Long asesoriaId) {
        return SuscripcionasesoriaService.contarEstudiantesPorAsesoria(asesoriaId);
    }
}
