package com.example.integradorOstric.service;

import com.example.integradorOstric.domain.Odontologo;
import com.example.integradorOstric.exception.BadRequestException;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import com.example.integradorOstric.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    private final Logger logger = Logger.getLogger(OdontologoService.class);

    public Odontologo guardarOdontologo(Odontologo odontologo){
        logger.info("Se guardo un nuevo odontologo en la tabla ODONTOLOGOS");
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologo(Long id){
        logger.info("Se realizo la busca del odontologo con id " + id + " en la tabla ODONTOLOGOS");
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> listarOdontologos(){
        logger.info("Se realizo una busqueda de todos los odontologos en la tabla ODONTOLOGOS");
        return odontologoRepository.findAll();
    }

    public Odontologo actualizarOdontologo(Odontologo newOdontologo)  {
        if(newOdontologo != null){
            return odontologoRepository.save(newOdontologo);
        }
        logger.info("Se actualizo el odontonlogo con id " + newOdontologo.getId() + " en la tabla ODONTOLOGOS");
        return new Odontologo();
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(id);
        logger.info("Se elimino el odontologo con id " + id + "de la tabla ODONTOLOGOS");
        if(odontologoBuscado.isPresent()){
            odontologoRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Error, no existe el odontologo con el id " + id);
        }
    }
}
