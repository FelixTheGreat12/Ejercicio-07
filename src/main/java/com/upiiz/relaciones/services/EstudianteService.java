package com.upiiz.relaciones.services;

import com.upiiz.relaciones.models.Estudiante;
import com.upiiz.relaciones.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    public Estudiante findById(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante update(Long id, Estudiante estudiante) {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(id);
        if (estudianteOpt.isPresent()) {
            Estudiante estudianteUpdate = estudianteOpt.get();
            estudianteUpdate.setNombre(estudiante.getNombre());
            estudianteUpdate.setCorreo(estudiante.getCorreo());
            estudianteUpdate.setPerfil(estudiante.getPerfil());
            estudianteUpdate.setTutor(estudiante.getTutor());

            return estudianteRepository.save(estudianteUpdate);
        }
        return null;
    }

    public Boolean delete(Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
