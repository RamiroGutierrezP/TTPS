package com.ttps.proyecto.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioRequestDto {
    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    private String apellido;
    @NotNull(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;
    private String foto;
    @NotNull(message = "El dni es obligatorio")
    private String dni;
    private String password;
}
