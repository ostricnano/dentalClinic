package com.example.integradorOstric.security;

import com.example.integradorOstric.domain.Usuario;
import com.example.integradorOstric.domain.UsuarioRol;
import com.example.integradorOstric.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();

        String passwordAdmin = cifrador.encode("12345678");
        String passwordUser = cifrador.encode("12345678");
        String passwordUser1 = cifrador.encode("12345678");
        usuarioRepository.save(new Usuario("Mariano","ostric","o@gmail.com",passwordAdmin,UsuarioRol.ROLE_ADMIN));
        usuarioRepository.save(new Usuario("Juan","Perez","j@gmail.com",passwordUser,UsuarioRol.ROLE_USER));
        usuarioRepository.save(new Usuario("Alberto","Fernandez","f@gmail.com",passwordUser1,UsuarioRol.ROLE_USER));
    }
}
