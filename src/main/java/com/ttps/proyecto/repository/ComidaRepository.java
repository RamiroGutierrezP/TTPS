package com.ttps.proyecto.repository;

import com.ttps.proyecto.model.Comida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComidaRepository extends JpaRepository<Comida, Long> {
}
