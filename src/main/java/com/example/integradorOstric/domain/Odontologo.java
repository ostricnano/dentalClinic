package com.example.integradorOstric.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private String numeroDeMatricula;
    @OneToMany(mappedBy = "odontologo",cascade = CascadeType.ALL)
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo(String apellido, String nombre, String numeroDeMatricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public Odontologo() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroDeMatricula() {
        return numeroDeMatricula;
    }

    public void setNumeroDeMatricula(String numeroDeMatricula) {
        this.numeroDeMatricula = numeroDeMatricula;
    }
}

