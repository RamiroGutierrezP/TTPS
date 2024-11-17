package com.ttps.proyecto.repository;

import com.ttps.proyecto.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByNombre(String nombre);
}
