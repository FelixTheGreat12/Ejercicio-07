package com.upiiz.relaciones.controllers;

import com.upiiz.relaciones.models.Estudiante;
import com.upiiz.relaciones.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiante")
public class EstudianteController {
 @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> getAllEstudiantes() {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @PostMapping
    public ResponseEntity<Estudiante> saveEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante savedEstudiante = estudianteService.save(estudiante);
        URI location = URI.create("/api/v1/Estudiante/" + savedEstudiante.getId());
        return ResponseEntity.created(location).body(savedEstudiante);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.findById(id);
        return estudiante != null ? ResponseEntity.ok(estudiante) : ResponseEntity.notFound().build();
    }

     @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        Estudiante updatedEstudiante = estudianteService.update(id, estudiante);
        return updatedEstudiante != null ? ResponseEntity.ok(updatedEstudiante) : ResponseEntity.notFound().build();
    }

    // Eliminar un Tutor por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable Long id) {
        if (estudianteService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
