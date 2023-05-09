package com.example.integradorOstric.service;

import com.example.integradorOstric.domain.Usuario;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import com.example.integradorOstric.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public Optional<Usuario> buscarUsuario(Long id){
        return usuarioRepository.findById(id);
    }

    public List<Usuario> listarUsuarios (){
        return usuarioRepository.findAll();
    }

    public Usuario actualizarUsuario(Usuario newUsuario){
        if(newUsuario != null){
            return usuarioRepository.save(newUsuario);
        }
        return new Usuario();
    }

    public void eliminarUsuario(Long id) throws ResourceNotFoundException{
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
        if(usuarioBuscado.isPresent()){
            usuarioRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Error, no existe el usuario con el id " + id);
        }
    }
}
