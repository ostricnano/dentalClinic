package com.example.integradorOstric.controller;


import com.example.integradorOstric.domain.Odontologo;
import com.example.integradorOstric.domain.Paciente;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import com.example.integradorOstric.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/pacientes")
public class PacienteCntroller {
    private PacienteService pacienteService;
    @Autowired
    public PacienteCntroller(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @PostMapping("/registrar")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        ResponseEntity response = null;
        if(pacienteService.guardarPaciente(paciente)==null){
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            response = ResponseEntity.ok(paciente);
        }
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable("id") Long id){
        ResponseEntity response = null;
        if(pacienteService.buscarPaciente(id)==null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Optional<Paciente> paciente = pacienteService.buscarPaciente(id);
            response = ResponseEntity.ok().body(paciente);
        }
        return response;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes(Paciente paciente){
        ResponseEntity response = null;
        if(pacienteService.listarPacientes()== null){
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            List<Paciente> pacientes = pacienteService.listarPacientes();
            response = ResponseEntity.ok(pacientes);
        }
        return response;
    }
    @CrossOrigin
    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente newPaciente){
        ResponseEntity response = null;
        if(pacienteService.buscarPaciente(newPaciente.getId())==null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            response = new ResponseEntity(pacienteService.actualizarPaciente(newPaciente),HttpStatus.OK);
        }
        return response;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Se elimino el paciente con id " + id );
    }
}
