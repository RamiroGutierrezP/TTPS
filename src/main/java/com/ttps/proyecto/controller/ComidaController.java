package com.ttps.proyecto.controller;

import com.ttps.proyecto.dto.request.ComidaRequestDto;
import com.ttps.proyecto.dto.response.ResponseDto;
import com.ttps.proyecto.service.ComidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ttps/food")
public class ComidaController {

    private final ComidaService comidaService;

    public ComidaController(ComidaService comidaService) {
        this.comidaService = comidaService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> crearComida(@Valid @RequestBody ComidaRequestDto comida) {
        comidaService.crearComida(comida);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Comida creada con éxito"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> actualizarComida(@PathVariable Long id, @Valid @RequestBody ComidaRequestDto comida) {
        comidaService.actualizarComida(id, comida);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("Comida actualizada con éxito"));
    }

    //TODO: Crear ComidaResponseDto
    @GetMapping("/")
    public ResponseEntity<?> listarComidas() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(comidaService.listarComidas());
    }
}
