package com.upiiz.relaciones.services;

import com.upiiz.relaciones.models.Tutor;
import com.upiiz.relaciones.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    public Tutor findById(Long id) {
        return tutorRepository.findById(id).orElse(null);
    }

    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public Tutor update(Long id, Tutor tutor) {
        Optional<Tutor> tutorOpt = tutorRepository.findById(id);
        if (tutorOpt.isPresent()) {
            // Si el valor esta presente retorna el valor com .get()
            Tutor tutorUpdate = tutorOpt.get();
            tutorUpdate.setNombre(tutor.getNombre());
            tutorUpdate.setCorreo(tutor.getCorreo());
            tutorUpdate.setEscolaridad(tutor.getEscolaridad());

            return tutorRepository.save(tutorUpdate);
        }
        return null;
    }

    public Boolean delete(Long id) {
        if (tutorRepository.existsById(id)) {
            tutorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
