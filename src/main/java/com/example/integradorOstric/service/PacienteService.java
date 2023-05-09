package com.example.integradorOstric.service;

import com.example.integradorOstric.domain.Paciente;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import com.example.integradorOstric.repository.DomicilioRepository;
import com.example.integradorOstric.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;
    private DomicilioRepository domicilioRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, DomicilioRepository domicilioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioRepository = domicilioRepository;
    }

    private final Logger logger = Logger.getLogger(PacienteService.class);

    public Paciente guardarPaciente (Paciente paciente){
        logger.info("Se guardo un paciente en la tabla de PACIENTES" + paciente);
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente (Long id){
        logger.info("Se realizo la busqueda del paciente con id " + id + "en la tabla PACIENTES");
        return pacienteRepository.findById(id);
    }
    public List<Paciente> listarPacientes (){
        logger.info("Se realizo la busqueda de todos los pacientes en la tabla de PACIENTES");
        return pacienteRepository.findAll();
    }
    public Paciente actualizarPaciente(Paciente newPaciente){
        if(newPaciente != null){
            return pacienteRepository.save(newPaciente);
        }
        logger.info("Se actualizo el paciente con id " + newPaciente.getId() + " de la tabla PACIENTES");
        return new Paciente();
    }
    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        logger.info("Se elimino el paciente con id " + id + " de la tabla PACIENTES");
        if(pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Error, no existe el paciente con el id " + id);
        }
    }
}
