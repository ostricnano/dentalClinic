package com.example.integradorOstric.dto;

import com.example.integradorOstric.domain.Odontologo;
import com.example.integradorOstric.domain.Paciente;
import com.example.integradorOstric.domain.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto implements Serializable {
    private Long id;
    private Long odontologo_id;
    private Long paciente_id;
    private LocalDate fecha;
    private String nombre_odontologo;
    private String nombre_paciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Long odontologo_id) {
        this.odontologo_id = odontologo_id;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre_odontologo() {
        return nombre_odontologo;
    }

    public void setNombre_odontologo(String nombre_odontologo) {
        this.nombre_odontologo = nombre_odontologo;
    }

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }

    @Override
    public String toString() {
        return "TurnoDto{" + '\n' +
                "id=" + id + '\n' +
                "odontologo_id=" + odontologo_id + '\n' +
                "paciente_id=" + paciente_id + '\n' +
                "fecha=" + fecha + '\n' +
                "nombre_odontologo=" + nombre_odontologo + '\n' +
                "nombre_paciente=" + nombre_paciente + '\n' +
                '}';
    }
}
