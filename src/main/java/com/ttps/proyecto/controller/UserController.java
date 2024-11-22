package com.ttps.proyecto.controller;

import com.ttps.proyecto.dto.request.LoginRequestDto;
import com.ttps.proyecto.dto.request.UsuarioRequestDto;
import com.ttps.proyecto.dto.response.ResponseDto;
import com.ttps.proyecto.model.Usuario;
import com.ttps.proyecto.service.TokenService;
import com.ttps.proyecto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ttps/users")
public class UserController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> registrarUsuario(@Valid @RequestBody UsuarioRequestDto usuario) {
        usuarioService.registrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Usuario creado con éxito"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDto usuario) {
        usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("Usuario actualizado con éxito"));
    }

    //TODO: Crear UsuarioResponseDto
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.getUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {

        if (usuarioService.isLoginSuccessful(loginRequestDto.getEmail(), loginRequestDto.getPassword())) {
            String token = tokenService.generateToken(loginRequestDto.getEmail(), 30);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(token)); //TODO: Crear LoginResponseDto
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto("Usuario o contraseña incorrectos"));

    }

}
