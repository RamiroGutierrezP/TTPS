package com.ttps.proyecto.service;

import com.ttps.proyecto.dto.request.ComidaRequestDto;
import com.ttps.proyecto.model.Comida;
import com.ttps.proyecto.repository.ComidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    public Comida crearComida(ComidaRequestDto comidaRequestDto) {
        Comida comida = convertToEntity(comidaRequestDto);
        return comidaRepository.save(comida);
    }

    public Comida actualizarComida(Long id, ComidaRequestDto comidaRequestDto){

//        Comida comida = comidaRepository.findById(id).orElse(null);
        Comida comida = comidaRepository.getReferenceById(id); //TODO: buscar
        if (comida == null) {
            throw new RuntimeException("Comida no encontrada");
        }

        comida.setNombre(nonNull(comidaRequestDto.getNombre()) ? comidaRequestDto.getNombre() : comida.getNombre());
        comida.setPrecio(nonNull(comidaRequestDto.getPrecio()) ? comidaRequestDto.getPrecio() : comida.getPrecio());
        comida.setTipo(nonNull(comidaRequestDto.getTipo()) ? comidaRequestDto.getTipo() : comida.getTipo());
        comida.setEsVegetariano(nonNull(comidaRequestDto.getEsVegetariano()) ? comidaRequestDto.getEsVegetariano() : comida.isEsVegetariano());

        return comidaRepository.save(comida);
    }

    public List<Comida> listarComidas() {
        return comidaRepository.findAll();
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
