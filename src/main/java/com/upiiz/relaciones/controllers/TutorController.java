package com.upiiz.relaciones.controllers;

import com.upiiz.relaciones.models.Tutor;
import com.upiiz.relaciones.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping
    public ResponseEntity<List<Tutor>> getAllTutors() {
        return ResponseEntity.ok(tutorService.findAll());
    }

    @PostMapping
    public ResponseEntity<Tutor> saveTutor(@RequestBody Tutor tutor) {
        Tutor savedtTutor = tutorService.save(tutor);
        URI location = URI.create("/api/v1/tutor/" + savedtTutor.getId());
        return ResponseEntity.created(location).body(savedtTutor);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getTutorById(@PathVariable Long id) {
        Tutor tutor = tutorService.findById(id);
        return tutor != null ? ResponseEntity.ok(tutor) : ResponseEntity.notFound().build();
    }

     @PutMapping("/{id}")
    public ResponseEntity<Tutor> updateTutor(@PathVariable Long id, @RequestBody Tutor tutor) {
        Tutor updatedTutor = tutorService.update(id, tutor);
        return updatedTutor != null ? ResponseEntity.ok(updatedTutor) : ResponseEntity.notFound().build();
    }

    // Eliminar un Tutor por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable Long id) {
        if (tutorService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
