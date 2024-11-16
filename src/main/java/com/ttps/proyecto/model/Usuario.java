package com.ttps.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Usuario extends Persona {
    private String dni;

    @OneToMany (mappedBy = "usuario")
    private List<Sugerencia> misSugerencias;

    @OneToMany (mappedBy = "usuario")
    private List<Compra> misCompras;
}
