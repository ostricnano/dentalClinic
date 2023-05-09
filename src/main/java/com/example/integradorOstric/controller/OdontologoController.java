package com.example.integradorOstric.controller;

import com.example.integradorOstric.domain.Odontologo;
import com.example.integradorOstric.exception.BadRequestException;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import com.example.integradorOstric.service.OdontologoService;
import com.example.integradorOstric.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity response = null;
        if(odontologoService.guardarOdontologo(odontologo) == null){
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            response = ResponseEntity.ok(odontologo);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable("id") Long id){
        ResponseEntity response = null;
        if(odontologoService.buscarOdontologo(id) == null){
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            Optional<Odontologo> odontologo = odontologoService.buscarOdontologo((id));
            response = ResponseEntity.ok(odontologo);
        }
        return response;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        ResponseEntity response = null;
        if (odontologoService.listarOdontologos() == null){
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }else  {
            List<Odontologo> odontologos = odontologoService.listarOdontologos();
            response = ResponseEntity.ok(odontologos);
        }
        return response;
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo newOdontologo) throws BadRequestException {
        ResponseEntity response = null;
        if(odontologoService.buscarOdontologo(newOdontologo.getId()) == null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            response = new ResponseEntity(odontologoService.actualizarOdontologo(newOdontologo),HttpStatus.OK);
        }
        return response;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se elimino el odontologo con id " + id );
    }
}
