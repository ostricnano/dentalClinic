package com.example.integradorOstric.service;

import com.example.integradorOstric.domain.Odontologo;
import com.example.integradorOstric.domain.Paciente;
import com.example.integradorOstric.domain.Turno;
import com.example.integradorOstric.dto.TurnoDto;
import com.example.integradorOstric.exception.BadRequestException;
import com.example.integradorOstric.exception.GlobalExceptions;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import com.example.integradorOstric.repository.OdontologoRepository;
import com.example.integradorOstric.repository.PacienteRepository;
import com.example.integradorOstric.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRepository turnoRepository;
    private OdontologoRepository odontologoRepository;
    private PacienteRepository pacienteRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository) {
        this.turnoRepository = turnoRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
    }
    @Autowired
    ObjectMapper mapper;
    private final Logger logger = Logger.getLogger(TurnoService.class);

    public void guardarTurno(TurnoDto turno) throws BadRequestException {
        try{
            convertirTurnoATurnoDto(turnoRepository.save(convertirTurnoDTOaTurno(turno)));
            logger.info("Se guardo un turno en la tabla TURNOS");
        } catch (Exception ex){
            throw new BadRequestException("Error. el id del odontologo o paciente es incorrecto");
        }

    }
    public Optional<TurnoDto> buscarTurno(Long id) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        logger.info("Se realizo la busca del turno con id " + id + " en la tabla TURNOS");
        if(turnoBuscado.isPresent()){
            return Optional.of(convertirTurnoATurnoDto((turnoBuscado.get())));
        }else {
            return Optional.empty();
        }
    }
    public Optional<List<TurnoDto>> listarTurnos () {
        List<Turno> listaDeTurnos = turnoRepository.findAll();
        List<TurnoDto> listaTurnoDto = new ArrayList<>();
        logger.info("Se realizo la busca de todos los turnos en la tabla TURNOS");
        for (Turno listaDeTurno : listaDeTurnos) {
            listaTurnoDto.add(convertirTurnoATurnoDto(listaDeTurno));
        }
        if(listaTurnoDto.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(listaTurnoDto);
        }
    }
    public TurnoDto actualizarTurno (TurnoDto newTurno){
        logger.info("Se actualizo el turno con el id " + newTurno.getId());
        return convertirTurnoATurnoDto(turnoRepository.save(convertirTurnoDTOaTurno(newTurno)));
    }
    public void eliminarTurno (Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        logger.info("Se elimo el turno con id " + id + " de la tabla TURNOS");
        if(turnoBuscado.isPresent()){
            turnoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Error, no existe el turno con el id " + id);
        }
    }
    private Turno convertirTurnoDTOaTurno(TurnoDto turnoDTO){
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        paciente.setId(turnoDTO.getPaciente_id());
        paciente.setNombre(turnoDTO.getNombre_paciente());
        odontologo.setId(turnoDTO.getOdontologo_id());
        odontologo.setNombre(turnoDTO.getNombre_odontologo());
        //vincular los objetos
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        //el turno esta listo
        return turno;
    }
    private TurnoDto convertirTurnoATurnoDto(Turno turno){
        TurnoDto turnoDto = new TurnoDto();

        turnoDto.setId(turno.getId());
        turnoDto.setOdontologo_id(turno.getOdontologo().getId());
        turnoDto.setPaciente_id(turno.getPaciente().getId());
        turnoDto.setFecha(turno.getFecha());
        turnoDto.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoDto.setNombre_paciente(turno.getPaciente().getNombre());
        return turnoDto;
    }
}
