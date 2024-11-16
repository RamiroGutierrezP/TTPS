package com.ttps.proyecto.model;

import com.ttps.proyecto.enums.TipoComida;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Double precio;
    @Enumerated(EnumType.STRING)
    private TipoComida tipo;
    private boolean esVegetariano;
}
