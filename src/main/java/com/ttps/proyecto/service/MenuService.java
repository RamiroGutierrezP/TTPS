package com.ttps.proyecto.service;

import com.ttps.proyecto.dto.request.MenuRequestDto;
import com.ttps.proyecto.model.Comida;
import com.ttps.proyecto.model.Menu;
import com.ttps.proyecto.repository.ComidaRepository;
import com.ttps.proyecto.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    public Menu crearMenu(MenuRequestDto menu) {
        Menu menuNuevo = convertirMenuDtoAMenu(menu);
        return menuRepository.save(menuNuevo);
    }

    public Menu actualizarMenu(Long id, MenuRequestDto menuRequestDto) {
        Menu menu = menuRepository.findById(id).orElse(null);

        if (menu == null) {
            throw new RuntimeException("Menu no encontrado");
        }

        Menu menuNuevo = convertirMenuDtoAMenu(menuRequestDto);
        return menuRepository.save(menuNuevo);
    }

    public List<Menu> listarMenus() {
        return menuRepository.findAll();
    }

    private Menu convertirMenuDtoAMenu(MenuRequestDto menuRequestDto) {
        Comida entrada = comidaRepository.findById(menuRequestDto.getEntradaId())
                .orElseThrow(() -> new IllegalArgumentException("Entrada con ID " + menuRequestDto.getEntradaId() + " no encontrada"));

        Comida platoPrincipal = comidaRepository.findById(menuRequestDto.getPlatoPrincipalId())
                .orElseThrow(() -> new IllegalArgumentException("Plato principal con ID " + menuRequestDto.getPlatoPrincipalId() + " no encontrado"));

        Comida postre = comidaRepository.findById(menuRequestDto.getPostreId())
                .orElseThrow(() -> new IllegalArgumentException("Postre con ID " + menuRequestDto.getPostreId() + " no encontrado"));

        Comida bebida = comidaRepository.findById(menuRequestDto.getBebidaId())
                .orElseThrow(() -> new IllegalArgumentException("Bebida con ID " + menuRequestDto.getBebidaId() + " no encontrada"));

        Menu menu = new Menu();
        menu.setNombre(menuRequestDto.getNombre());
        menu.setPrecioTotal(calcularPrecioTotal(entrada, platoPrincipal, postre, bebida));
        menu.setEntrada(entrada);
        menu.setPlatoPrincipal(platoPrincipal);
        menu.setPostre(postre);
        menu.setBebida(bebida);
        menu.setEsVegetariano(esVegetariano(entrada, platoPrincipal, postre, bebida));
        return menu;
    }

    private double calcularPrecioTotal(Comida entrada, Comida platoPrincipal, Comida postre, Comida bebida) {
        return entrada.getPrecio()
                + platoPrincipal.getPrecio()
                + postre.getPrecio()
                + bebida.getPrecio();
    }

    private boolean esVegetariano(Comida entrada, Comida platoPrincipal, Comida postre, Comida bebida) {
        return entrada.isEsVegetariano()
                && platoPrincipal.isEsVegetariano()
                && postre.isEsVegetariano()
                && bebida.isEsVegetariano();
    }

}
