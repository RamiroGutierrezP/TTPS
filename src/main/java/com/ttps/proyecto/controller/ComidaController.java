package com.ttps.proyecto.controller;

import com.ttps.proyecto.dto.request.ComidaRequestDto;
import com.ttps.proyecto.dto.response.ResponseDto;
import com.ttps.proyecto.model.Comida;
import com.ttps.proyecto.service.ComidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ttps/food")
public class ComidaController {

    @Autowired
    private ComidaService comidaService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> crearComida(@Valid @RequestBody ComidaRequestDto comida) {
        comidaService.crearComida(comida);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Comida creada con éxito"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarComida(@PathVariable Long id, @RequestBody ComidaRequestDto comida) {
        comidaService.actualizarComida(id, comida);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("Comida actualizada con éxito"));
    }

    @GetMapping("/")
    public ResponseEntity<List<Comida>> listarComidas() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comidaService.listarComidas());
    }
}
