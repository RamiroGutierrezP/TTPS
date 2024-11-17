package com.ttps.proyecto.controller;

import com.ttps.proyecto.dto.request.MenuRequestDto;
import com.ttps.proyecto.dto.response.ResponseDto;
import com.ttps.proyecto.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ttps/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> crearMenu(@Valid @RequestBody MenuRequestDto menu) {
        menuService.crearMenu(menu);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Menu creado con éxito"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMenu(@PathVariable Long id, @Valid @RequestBody MenuRequestDto menu) {
        menuService.actualizarMenu(id, menu);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("Menu actualizado con éxito"));
    }

    //TODO: Crear MenuResponseDto
    @GetMapping("/")
    public ResponseEntity<?> listarMenus() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuService.listarMenus());
    }
}
