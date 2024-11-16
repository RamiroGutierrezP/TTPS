package com.ttps.proyecto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioRequestDto {
    private String nombre;
    private String apellido;
    private String email;
    private String foto;
    private String rol;
    private String dni;
}
