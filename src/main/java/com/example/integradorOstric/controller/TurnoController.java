package com.example.integradorOstric.controller;

import com.example.integradorOstric.domain.Odontologo;
import com.example.integradorOstric.domain.Paciente;
import com.example.integradorOstric.domain.Turno;
import com.example.integradorOstric.dto.TurnoDto;
import com.example.integradorOstric.exception.BadRequestException;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import com.example.integradorOstric.service.OdontologoService;
import com.example.integradorOstric.service.PacienteService;
import com.example.integradorOstric.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> guardarTurno (@RequestBody TurnoDto turno) throws BadRequestException {
        ResponseEntity<TurnoDto> response;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologo_id());
        turnoService.guardarTurno(turno);
        return ResponseEntity.ok("Se agrego el turno correctamente " + turno);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurno (@PathVariable("id") Long id) throws BadRequestException {
        Optional<TurnoDto> turnobuscado = turnoService.buscarTurno(id);
        if(turnobuscado.isPresent()){
            return ResponseEntity.ok(turnobuscado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<TurnoDto>> listarTurnos() {
        Optional<List<TurnoDto>> listaDeTurnos = turnoService.listarTurnos();
        if(turnoService.listarTurnos()==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(listaDeTurnos.get());
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@RequestBody TurnoDto newTurno) throws Exception {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(newTurno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(newTurno.getOdontologo_id());
        Optional<TurnoDto> turnoBuscado = turnoService.buscarTurno(newTurno.getId());
        if(turnoBuscado.isPresent() && odontologoBuscado.isPresent() && pacienteBuscado.isPresent()){
            return ResponseEntity.ok(turnoService.actualizarTurno(newTurno));
        }else {
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable("id") Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Se elimino el turno con id " + id );
    }
}
