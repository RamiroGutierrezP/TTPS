package com.ttps.proyecto.repository;

import com.ttps.proyecto.model.Comida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComidaRepository extends JpaRepository<Comida, Long> {
    Optional<Comida> findByNombre(String nombre);
}
