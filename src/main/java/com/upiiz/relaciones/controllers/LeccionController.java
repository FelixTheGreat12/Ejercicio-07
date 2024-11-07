package com.upiiz.relaciones.controllers;

import com.upiiz.relaciones.models.Leccion;
import com.upiiz.relaciones.services.LeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/leccion")
public class LeccionController {
    @Autowired
    private LeccionService leccionService;

    @GetMapping
    public ResponseEntity<List<Leccion>> getAllLeccions() {
        return ResponseEntity.ok(leccionService.findAll());
    }

    @PostMapping
    public ResponseEntity<Leccion> saveLeccion(@RequestBody Leccion leccion) {
        Leccion savedLeccion = leccionService.save(leccion);
        URI location = URI.create("/api/v1/leccion/" + savedLeccion.getId());
        return ResponseEntity.created(location).body(savedLeccion);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Leccion> getLeccionById(@PathVariable Long id) {
        Leccion leccion = leccionService.findById(id);
        return leccion != null ? ResponseEntity.ok(leccion) : ResponseEntity.notFound().build();
    }

     @PutMapping("/{id}")
    public ResponseEntity<Leccion> updateLeccion(@PathVariable Long id, @RequestBody Leccion leccion) {
        Leccion updatedLeccion = leccionService.update(id,leccion);
        return updatedLeccion != null ? ResponseEntity.ok(updatedLeccion) : ResponseEntity.notFound().build();
    }

    // Eliminar un Tutor por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeccion(@PathVariable Long id) {
        if (leccionService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
