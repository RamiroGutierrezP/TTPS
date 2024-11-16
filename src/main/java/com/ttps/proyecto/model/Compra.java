package com.ttps.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private Double precioTotal;
    private Date fecha;
    private String codigoQR;

    @ManyToMany
    private List<Menu> menus;

    @ManyToMany
    private List<Comida> comidas;

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;
}
