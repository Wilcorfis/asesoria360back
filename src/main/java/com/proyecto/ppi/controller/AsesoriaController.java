package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.Asesoria;
import com.proyecto.ppi.entity.Usuario;
import com.proyecto.ppi.entity.Horario;
import com.proyecto.ppi.entity.Asignatura;
import com.proyecto.ppi.repository.AsignaturaRepository;
import com.proyecto.ppi.repository.HorarioRepository;
import com.proyecto.ppi.repository.UsuarioRepository;
import com.proyecto.ppi.service.AsesoriaService;
import com.proyecto.ppi.service.RetroalimentacionService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/asesorias")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AsesoriaController {
    private final String SECRET_KEY = "abcdefghijklmnopqrstuvwxy1234567890abcdefghijklmnopqrstuv"; // Mínimo 256 bits
    private final SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    @Autowired
    private AsesoriaService asesoriaService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @GetMapping("/estudiante/{idEstudiante}")
    public List<Asesoria> getAsesoriasPorEstudiante(@PathVariable Long idEstudiante) {
        return asesoriaService.listarAsesoriasPorEstudiante(idEstudiante);
    }
    @GetMapping("/tutor/{idTutor}")
    public List<Asesoria> getAsesoriasPortutor(@PathVariable Long idTutor) {
        return asesoriaService.listarAsesoriasPorTutor(idTutor);
    }

    // Obtener todas las asesorías
    @GetMapping
    public List<Asesoria> getAllAsesorias() {
        return asesoriaService.getAllAsesorias();
    }

    // Obtener asesoría por ID_asesoria
    @GetMapping("/{id}")
    public Asesoria getAsesoriaById(@PathVariable Long id) {
        return asesoriaService.getAsesoriaById(id);
    }

    // Crear nueva asesoría con validación
    @PostMapping
    public Asesoria createAsesoria(@Valid @RequestBody Asesoria asesoria, @RequestHeader("Authorization") String authorization) {

    Horario horario = horarioRepository.findById(asesoria.getHorario().getId_horario())
            .orElseThrow(() -> new IllegalArgumentException("horario no encontrado"));
    Asignatura asignatura = asignaturaRepository.findById(asesoria.getAsignatura().getId_asignatura())
            .orElseThrow(() -> new IllegalArgumentException("asignatura no encontrado"));
    Usuario usuario = usuarioRepository.findById(asesoria.getTutor().getId_usuario())
            .orElseThrow(() -> new IllegalArgumentException("usuario no encontrado"));

    asesoria.setTutor(usuario);
    asesoria.setAsignatura(asignatura);
    asesoria.setHorario(horario);
        if (asesoria.getTutor().getCorreo().equals(validateToken(authorization).getSubject())){
            return asesoriaService.saveAsesoria(asesoria);

        }
        throw new IllegalArgumentException("El correo del tutor no coincide con el token");
    }

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

    // Actualizar asesoría existente con validación
    @PutMapping("/{id}")
    public Asesoria updateAsesoria(@PathVariable Long id, @Valid @RequestBody Asesoria asesoriaDetails) {
        return asesoriaService.updateAsesoria(id, asesoriaDetails);
    }

    @DeleteMapping("/{fkIdAsesoria}")
    public void eliminarRetroalimentacionYAsesoria(@PathVariable Long fkIdAsesoria) {
        asesoriaService.eliminarRetroalimentacionYAsesoria(fkIdAsesoria);
    }
}

