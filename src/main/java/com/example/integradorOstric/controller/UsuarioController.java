package com.example.integradorOstric.controller;

import com.example.integradorOstric.domain.Usuario;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import com.example.integradorOstric.jwt.JWTUtil;
import com.example.integradorOstric.security.PasswordEncoder;
import com.example.integradorOstric.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {

    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
       ResponseEntity response = null;
        String hashedPassword = passwordEncoder.bCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setPassword(hashedPassword);
       if(usuarioService.guardarUsuario(usuario) == null){
           response= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }else{
           response = ResponseEntity.ok(usuario);
       }
       return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable("id") Long id){
        ResponseEntity response = null;
        if(usuarioService.buscarUsuario(id) == null){
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Optional<Usuario> usuario =usuarioService.buscarUsuario(id);
            response = ResponseEntity.ok(usuario);
        }
        return response;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        ResponseEntity response = null;
        if(usuarioService.listarUsuarios() == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            response = ResponseEntity.ok(usuarios);
        }
        return response;
    }
    @PostMapping("/actualizar")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario newUsuario) {
        ResponseEntity response = null;
        if(usuarioService.buscarUsuario(newUsuario.getId()) != null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            response = ResponseEntity.ok(newUsuario);
        }
        return response;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id) throws ResourceNotFoundException{
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Se elimino el usuario con id " + id );
    }

}
