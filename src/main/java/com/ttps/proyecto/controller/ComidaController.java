package com.ttps.proyecto.controller;

import com.ttps.proyecto.dto.request.ComidaRequestDto;
import com.ttps.proyecto.service.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ttps/food")
public class ComidaController {

    @Autowired
    private ComidaService comidaService;

    @PostMapping("/")
    public ResponseEntity<?> crearComida(@RequestBody ComidaRequestDto comida) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comidaService.crearComida(comida));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarComida(@PathVariable Long id, @RequestBody ComidaRequestDto comida) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comidaService.actualizarComida(id, comida));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> listarComidas() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comidaService.listarComidas());
    }
}
