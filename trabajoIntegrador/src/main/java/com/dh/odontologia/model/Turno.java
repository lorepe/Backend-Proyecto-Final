package com.dh.odontologia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="paciente_id",nullable = false)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name="odontologo_id", nullable = false)
    private Odontologo odontologo;
    private Date fecha;

    public Turno() {
    }

    public Long getId() {
        return id;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fecha=" + fecha +
                '}';
    }
}
