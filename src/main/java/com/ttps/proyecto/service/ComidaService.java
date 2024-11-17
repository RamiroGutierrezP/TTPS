package com.ttps.proyecto.service;

import com.ttps.proyecto.dto.request.ComidaRequestDto;
import com.ttps.proyecto.exceptions.AlreadyExistException;
import com.ttps.proyecto.exceptions.NotFoundException;
import com.ttps.proyecto.model.Comida;
import com.ttps.proyecto.repository.ComidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class ComidaService {

    private final ComidaRepository comidaRepository;

    public ComidaService(ComidaRepository comidaRepository) {
        this.comidaRepository = comidaRepository;
    }

    public void crearComida(ComidaRequestDto comidaRequestDto) {

        if (comidaRepository.findByNombre(comidaRequestDto.getNombre()).isPresent()) {
            throw new AlreadyExistException("Ya existe una comida con ese nombre");
        }

        Comida comida = convertToEntity(comidaRequestDto);
        comidaRepository.save(comida);
    }

    public void actualizarComida(Long id, ComidaRequestDto comidaRequestDto){

        Comida comida = comidaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró una comida con ID: " + id));

        if (nonNull(comidaRequestDto.getNombre())
                && !comida.getNombre().equals(comidaRequestDto.getNombre())
                && comidaRepository.findByNombre(comidaRequestDto.getNombre()).isPresent()) {
            throw new AlreadyExistException("Ya existe una comida con ese nombre");
        }

        actualizarComida(comida, comidaRequestDto);
        comidaRepository.save(comida);
    }

    public List<Comida> listarComidas() {
        return comidaRepository.findAll();
    }

    private void actualizarComida(Comida comida, ComidaRequestDto comidaRequestDto) {
        if (nonNull(comidaRequestDto.getNombre())) comida.setNombre(comidaRequestDto.getNombre());
        if (nonNull(comidaRequestDto.getPrecio())) comida.setPrecio(comidaRequestDto.getPrecio());
        if (nonNull(comidaRequestDto.getTipo())) comida.setTipo(comidaRequestDto.getTipo());
        if (nonNull(comidaRequestDto.getEsVegetariano())) comida.setEsVegetariano(comidaRequestDto.getEsVegetariano());
    }

    private Comida convertToEntity(ComidaRequestDto comidaRequestDto) {
        Comida comida = new Comida();
        comida.setNombre(comidaRequestDto.getNombre());
        comida.setPrecio(comidaRequestDto.getPrecio());
        comida.setTipo(comidaRequestDto.getTipo());
        comida.setEsVegetariano(comidaRequestDto.getEsVegetariano());
        return comida;
    }
}
