package com.ttps.proyecto.model;

import com.ttps.proyecto.enums.TipoPersona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String foto;
    @Enumerated(EnumType.STRING)
    private TipoPersona rol;
    private String password;
}
