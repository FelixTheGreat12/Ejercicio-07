package com.upiiz.relaciones.services;

import com.upiiz.relaciones.models.Perfil;
import com.upiiz.relaciones.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    public Perfil findById(Long id) {
        return perfilRepository.findById(id).orElse(null);
    }

    public Perfil save(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public Perfil update(Long id, Perfil perfil) {
        Optional<Perfil> perfilOpt = perfilRepository.findById(id);
        if (perfilOpt.isPresent()) {
            // Si el valor esta presente retorna el valor com .get()
            Perfil perfilUpdate = perfilOpt.get();
            perfilUpdate.setBiografia(perfil.getBiografia());
            perfilUpdate.setFoto(perfil.getFoto());
            return perfilRepository.save(perfilUpdate);
        }
        return null;
    }

    public Boolean delete(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
