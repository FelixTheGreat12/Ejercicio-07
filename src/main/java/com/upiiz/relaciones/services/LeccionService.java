package com.upiiz.relaciones.services;

import com.upiiz.relaciones.models.Leccion;
import com.upiiz.relaciones.repositories.LeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeccionService {

    @Autowired
    private LeccionRepository leccionRepository;

    public List<Leccion> findAll() {
        return leccionRepository.findAll();
    }

    public Leccion findById(Long id) {
        return leccionRepository.findById(id).orElse(null);
    }

    public Leccion save(Leccion leccion) {
        return leccionRepository.save(leccion);
    }

    public Leccion update(Long id, Leccion leccion) {
        Optional<Leccion> leccionOpt = leccionRepository.findById(id);
        if (leccionOpt.isPresent()) {
            // Si el valor esta presente retorna el valor com .get()
            Leccion leccionUpdate = leccionOpt.get();
            leccionUpdate.setTitulo(leccion.getTitulo());
            leccionUpdate.setContenido(leccion.getContenido());
            leccionUpdate.setCurso(leccion.getCurso());

            return leccionRepository.save(leccionUpdate);
        }
        return null;
    }

    public Boolean delete(Long id) {
        if (leccionRepository.existsById(id)) {
            leccionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
