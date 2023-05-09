package com.example.integradorOstric.service;

import com.example.integradorOstric.domain.Domicilio;
import com.example.integradorOstric.domain.Paciente;
import com.example.integradorOstric.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Test
    @Order(1)
    void guardarPaciente() {
        Domicilio domicilio = new Domicilio("Juncal","930","Retiro","CABA");
        Paciente paciente = new Paciente("Ostric","Mariano","35728564","o@gmail.com",
                LocalDate.of(2023,03,19), LocalDate.of(2023,03,23),domicilio);
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(1L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarPaciente() {
        //dado
        Domicilio domicilio = new Domicilio("Juncal","930","Retiro","CABA");
        Paciente paciente = new Paciente("Ostric","Mariano","35728564","o@gmail.com",
                LocalDate.of(2023,03,19), LocalDate.of(2023,03,23),domicilio);
        //cuando
        pacienteService.guardarPaciente(paciente);
        pacienteService.buscarPaciente(paciente.getId());
        //entonces
        assertNotNull(paciente);
    }

    @Test
    @Order(3)
    void listarPacientes() {
        //dado
        Domicilio domicilio= new Domicilio("Calle A","814","Salta Capital","Salta");
        Paciente paciente = new Paciente("Ostric","Mariano","35728564","o@gmail.com",
                LocalDate.of(2023,03,19), LocalDate.of(2023,03,23),domicilio);
        Domicilio domicilio1= new Domicilio("Calle A","814","Salta Capital","Salta");
        Paciente paciente1 = new Paciente("Ostric","Mariano","35728564","o@gmail.com",
                LocalDate.of(2023,03,19), LocalDate.of(2023,03,23),domicilio1);
        //cuando
        pacienteService.guardarPaciente(paciente);
        pacienteService.guardarPaciente(paciente1);
        List<Paciente> pacienteList = pacienteService.listarPacientes();
        //entonces
        assertTrue(pacienteList.size() >= 1);
    }

    @Test
    void actualizarPaciente() {
        //dado
        Domicilio domicilio= new Domicilio("Calle A","814","Salta Capital","Salta");
        Paciente paciente = new Paciente("Ostric","Mariano","35728564","o@gmail.com",
                LocalDate.of(2023,03,19), LocalDate.of(2023,03,23),domicilio);
        //cuando
        pacienteService.guardarPaciente(paciente);
        pacienteService.actualizarPaciente(paciente);
        //entonces
        assertNotNull(paciente);
    }

    @Test
    void eliminarPaciente() throws ResourceNotFoundException {
        //dado
        Domicilio domicilio= new Domicilio("Calle A","814","Salta Capital","Salta");
        Paciente paciente = new Paciente("Ostric","Mariano","35728564","o@gmail.com",
                LocalDate.of(2023,03,19), LocalDate.of(2023,03,23),domicilio);
        //cuando
        pacienteService.guardarPaciente(paciente);
        pacienteService.eliminarPaciente(paciente.getId());
        //entonces
        assertTrue(pacienteService.buscarPaciente(paciente.getId()).isEmpty());
    }
}