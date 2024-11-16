package com.ttps.proyecto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuRequestDto {
    private String nombre;
    private Double precioTotal;
    private Long entradaId;
    private Long platoPrincipalId;
    private Long postreId;
    private Long bebidaId;
    private Boolean esVegetariano;
}
