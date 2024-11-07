package com.upiiz.relaciones.services;

import com.upiiz.relaciones.models.Curso;
import com.upiiz.relaciones.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso update(Long id, Curso curso) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        if (cursoOpt.isPresent()) {
            // Si el valor esta presente retorna el valor com .get()
            Curso cursoUpdate = cursoOpt.get();
            cursoUpdate.setTitulo(curso.getTitulo());
            cursoUpdate.setDescripcion(curso.getDescripcion());
            return cursoRepository.save(cursoUpdate);
        }
        return null;
    }

    public Boolean delete(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
