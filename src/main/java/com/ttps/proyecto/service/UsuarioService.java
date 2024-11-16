package com.ttps.proyecto.service;

import com.ttps.proyecto.dto.request.UsuarioRequestDto;
import com.ttps.proyecto.exceptions.NotFoundException;
import com.ttps.proyecto.exceptions.AlreadyExistException;
import com.ttps.proyecto.model.Usuario;
import com.ttps.proyecto.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsers() {
        return usuarioRepository.findAll();
    }

    public void registrarUsuario(UsuarioRequestDto usuarioRequestDto) {

        if (usuarioRepository.findByEmail(usuarioRequestDto.getEmail()).isPresent()) {
            throw new AlreadyExistException("El email ya se encuentra registrado");
        }
        //TODO: Podríamos validar otros campos como el DNI...

        Usuario usuario = convertToEntity(usuarioRequestDto);
        usuarioRepository.save(usuario);
    }

    public void actualizarUsuario(Long id, UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Usuario no encontrado"));

        if (nonNull(usuarioRequestDto.getEmail())
                && !usuario.getEmail().equals(usuarioRequestDto.getEmail())
                && usuarioRepository.findByEmail(usuarioRequestDto.getEmail()).isPresent()) {
            throw new AlreadyExistException("El email ya se encuentra registrado");
        }

        actualizarUsuario(usuario, usuarioRequestDto);
        usuarioRepository.save(usuario);
    }

    private void actualizarUsuario(Usuario usuario, UsuarioRequestDto usuarioRequestDto) {
        if (nonNull(usuarioRequestDto.getNombre())) usuario.setNombre(usuarioRequestDto.getNombre());
        if (nonNull(usuarioRequestDto.getApellido())) usuario.setApellido(usuarioRequestDto.getApellido());
        if (nonNull(usuarioRequestDto.getEmail())) usuario.setEmail(usuarioRequestDto.getEmail());
        if (nonNull(usuarioRequestDto.getFoto())) usuario.setFoto(usuarioRequestDto.getFoto());
        if (nonNull(usuarioRequestDto.getRol())) usuario.setRol(usuarioRequestDto.getRol());
        if (nonNull(usuarioRequestDto.getDni())) usuario.setDni(usuarioRequestDto.getDni());
    }

    private Usuario convertToEntity(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRequestDto.getNombre());
        usuario.setApellido(usuarioRequestDto.getApellido());
        usuario.setEmail(usuarioRequestDto.getEmail());
        usuario.setFoto(usuarioRequestDto.getFoto());
        usuario.setRol(usuarioRequestDto.getRol());
        usuario.setDni(usuarioRequestDto.getDni());
        return usuario;
    }
}
