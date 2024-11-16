package com.ttps.proyecto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComidaRequestDto {
    private String nombre;
    private Double precio;
    private String tipo;
    private Boolean esVegetariano;
}
