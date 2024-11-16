package com.ttps.proyecto.model;

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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private double precioTotal;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Comida entrada;
    @ManyToOne
    private Comida platoPrincipal;
    @ManyToOne
    private Comida postre;
    @ManyToOne
    private Comida bebida;
    private boolean esVegetariano;
}
