package com.example.integradorOstric.service;

import com.example.integradorOstric.domain.Odontologo;
import com.example.integradorOstric.domain.Paciente;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologo() {
        //dado
        Odontologo odontologo = new Odontologo("perez","juan","123");
        //cuando
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologo);
        //entonces
        assertEquals(1L,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarOdontologo() {
        //dado
        Odontologo odontologo = new Odontologo("perez","juan","123");
        //cuando
        odontologoService.guardarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(odontologo.getId());
        //entonces
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    void listarOdontologos() {
        //dado
        Odontologo odontologo = new Odontologo("perez","juan","123");
        Odontologo odontologo1 = new Odontologo("perez","juan","123");
        //cuando
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.guardarOdontologo(odontologo1);

        List<Odontologo> odontologoList=odontologoService.listarOdontologos();
        //entonces
        assertTrue(odontologoList.size()>=1);
    }

    @Test
    @Order(4)
    void actualizarOdontologo() {
        //dado
        Odontologo odontologo = new Odontologo("perez","juan","123");
        //cuando
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.actualizarOdontologo(odontologo);
        //entonces
        assertNotNull(odontologo);
    }

    @Test
    @Order(5)
    void eliminarOdontologo() throws ResourceNotFoundException {
        //dado
        Odontologo odontologo = new Odontologo("perez","juan","123");
        //cuando
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.eliminarOdontologo(odontologo.getId());
        //entonces
        assertTrue(odontologoService.buscarOdontologo(odontologo.getId()).isEmpty());
    }
}