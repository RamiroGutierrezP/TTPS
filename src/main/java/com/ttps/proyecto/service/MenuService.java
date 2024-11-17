package com.ttps.proyecto.service;

import com.ttps.proyecto.dto.request.MenuRequestDto;
import com.ttps.proyecto.exceptions.AlreadyExistException;
import com.ttps.proyecto.exceptions.NotFoundException;
import com.ttps.proyecto.model.Comida;
import com.ttps.proyecto.model.Menu;
import com.ttps.proyecto.repository.ComidaRepository;
import com.ttps.proyecto.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class MenuService {

    private final MenuRepository menuRepository;
    private final ComidaRepository comidaRepository;

    public MenuService(MenuRepository menuRepository, ComidaRepository comidaRepository) {
        this.menuRepository = menuRepository;
        this.comidaRepository = comidaRepository;
    }

    public void crearMenu(MenuRequestDto menuRequestDto) {

        if (menuRepository.findByNombre(menuRequestDto.getNombre()).isPresent()) {
            throw new AlreadyExistException("Ya existe un menu con ese nombre");
        }

        Menu menu = convertirMenuDtoAMenu(menuRequestDto);
        menuRepository.save(menu);
    }

    public void actualizarMenu(Long id, MenuRequestDto menuRequestDto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró un menu con ID: " + id));

        if (nonNull(menuRequestDto.getNombre())
                && !menu.getNombre().equals(menuRequestDto.getNombre())
                && menuRepository.findByNombre(menuRequestDto.getNombre()).isPresent()) {
            throw new AlreadyExistException("Ya existe un menu con ese nombre");
        }

        actualizarMenu(menu, menuRequestDto);
        menuRepository.save(menu);
    }

    public List<Menu> listarMenus() {
        return menuRepository.findAll();
    }

    public void actualizarMenu(Menu menu, MenuRequestDto menuRequestDto){
        if (nonNull(menuRequestDto.getNombre())) menu.setNombre(menuRequestDto.getNombre());
        if (nonNull(menuRequestDto.getPrecioTotal())) menu.setPrecioTotal(menuRequestDto.getPrecioTotal());
        if (nonNull(menuRequestDto.getEntradaId())) menu.setEntrada(buscarComidaPorId(menuRequestDto.getEntradaId(), "entrada"));
        if (nonNull(menuRequestDto.getPlatoPrincipalId())) menu.setPlatoPrincipal(buscarComidaPorId(menuRequestDto.getPlatoPrincipalId(), "plato principal"));
        if (nonNull(menuRequestDto.getPostreId())) menu.setPostre(buscarComidaPorId(menuRequestDto.getPostreId(), "postre"));
        if (nonNull(menuRequestDto.getBebidaId())) menu.setBebida(buscarComidaPorId(menuRequestDto.getBebidaId(), "bebida"));
        if (nonNull(menuRequestDto.getEsVegetariano())) menu.setEsVegetariano(menuRequestDto.getEsVegetariano());
    }

    private Menu convertirMenuDtoAMenu(MenuRequestDto menuRequestDto) {

        Comida entrada = buscarComidaPorId(menuRequestDto.getEntradaId(), "entrada");
        Comida platoPrincipal = buscarComidaPorId(menuRequestDto.getPlatoPrincipalId(), "plato principal");
        Comida postre = buscarComidaPorId(menuRequestDto.getPostreId(), "postre");
        Comida bebida = buscarComidaPorId(menuRequestDto.getBebidaId(), "bebida");

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
        return entrada.getPrecio() + platoPrincipal.getPrecio() + postre.getPrecio() + bebida.getPrecio();
    }

    private boolean esVegetariano(Comida entrada, Comida platoPrincipal, Comida postre, Comida bebida) {
        return entrada.isEsVegetariano()
                && platoPrincipal.isEsVegetariano()
                && postre.isEsVegetariano()
                && bebida.isEsVegetariano();
    }

    private Comida buscarComidaPorId(Long id, String tipo) {
        return comidaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró una " + tipo + " con ID: " + id));
    }

}
