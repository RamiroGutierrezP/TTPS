package com.ttps.proyecto.dto.request;

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
public class MenuRequestDto {
    @NotNull(message = "El menu debe tener un nombre")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    private Double precioTotal;
    @NotNull(message = "Debes indicar la entrada")
    private Long entradaId;
    @NotNull(message = "Debes indicar el plato principal")
    private Long platoPrincipalId;
    @NotNull(message = "Debes indicar el postre")
    private Long postreId;
    @NotNull(message = "Debes indicar la bebida")
    private Long bebidaId;
    private Boolean esVegetariano;
}
