package com.ttps.proyecto.controller;

import com.ttps.proyecto.dto.request.MenuRequestDto;
import com.ttps.proyecto.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ttps/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/")
    public ResponseEntity<?> crearMenu(@RequestBody MenuRequestDto menu) {
        return ResponseEntity.ok().body(menuService.crearMenu(menu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMenu(@PathVariable Long id, @RequestBody MenuRequestDto menu) {
        return ResponseEntity.ok().body(menuService.actualizarMenu(id, menu));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> listarMenus() {
        return ResponseEntity.ok().body(menuService.listarMenus());
    }
}
