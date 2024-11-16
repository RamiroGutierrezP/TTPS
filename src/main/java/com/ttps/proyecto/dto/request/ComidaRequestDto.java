package com.ttps.proyecto.dto.request;

import com.ttps.proyecto.enums.TipoComida;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComidaRequestDto {
    @NotNull(message = "La comida debe tener un nombre")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotNull(message = "La comida debe tener un precio")
    @Positive(message = "El precio debe ser positivo")
    private Double precio;

    @NotNull(message = "Debes indicar el tipo de comida")
    private TipoComida tipo;

    @NotNull(message = "Debes indicar si la comida es vegetariana")
    private Boolean esVegetariano;
}
