package com.upiiz.relaciones.repositories;

import com.upiiz.relaciones.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

}
