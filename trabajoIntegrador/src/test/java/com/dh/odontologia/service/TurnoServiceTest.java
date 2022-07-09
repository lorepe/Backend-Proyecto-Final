package com.dh.odontologia.service;

import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.Set;

@SpringBootTest
class TurnoServiceTest {
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IDomicilioService domicilioService;
    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private ITurnoService turnoService;

    public void cargarDataSet() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Pepe");
        paciente.setApellido("Pepardo");
        paciente.setDni("123456789");
        Domicilio domicilio = new Domicilio();
        domicilioService.crearDomicilio(domicilio);
        domicilio.setCalle("Calle Falsa");
        domicilio.setNumero("123");
        domicilio.setLocalidad("Springfield");
        domicilio.setProvincia("Springfield");
        paciente.setDomicilio(domicilio);
        pacienteService.crearPaciente(paciente);
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Pepo");
        odontologo.setApellido("Pepardo");
        odontologo.setMatricula(123456);
        odontologoService.crearOdontologo(odontologo);
    }

    @Test
    public void crearTurnoTest() {
        cargarDataSet();
        Paciente paciente = pacienteService.leerPaciente(1L);
        Odontologo odontologo = odontologoService.leerOdontologo(1L);
        Turno turno = new Turno();
        Date date = new Date();
        turno.setFecha(date);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        Turno turnoCreado = turnoService.crearTurno(turno);
        assertNotNull(turnoService.leerTurno(turnoCreado.getId()));
    }

    @Test
    public void eliminarTurnoTest() {
        cargarDataSet();
        Paciente paciente = pacienteService.leerPaciente(1L);
        Odontologo odontologo = odontologoService.leerOdontologo(1L);
        Turno turno = new Turno();
        Date date = new Date();
        turno.setFecha(date);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turnoService.crearTurno(turno);
        turnoService.eliminarTurno(1L);
        Turno turnoRecibido = turnoService.leerTurno(1L);
        assertTrue(turnoRecibido == null);

    }

    @Test
    public void traerTodosTest() {
        cargarDataSet();
        Paciente paciente = pacienteService.leerPaciente(1L);
        Odontologo odontologo = odontologoService.leerOdontologo(1L);
        Turno turno = new Turno();
        Date date = new Date();
        turno.setFecha(date);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turnoService.crearTurno(turno);
        Set<Turno> turnos = turnoService.listarTurnos();

        assertTrue(!turnos.isEmpty());
        assertTrue(turnos.size() >= 1);
        System.out.println(turnos);
    }
    @Test
    public void editarTurnosTest() throws ResourceNotFoundExceptions {
        cargarDataSet();
        Paciente paciente = pacienteService.leerPaciente(1L);
        Odontologo odontologo = odontologoService.leerOdontologo(1L);
        Turno turno = new Turno();
        Date date = new Date();
        turno.setFecha(date);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        Turno t = turnoService.crearTurno(turno);
        Odontologo original = odontologoService.leerOdontologo(t.getId());
        Date fechaModificada = new Date(20222,2,1) ;
        t.setFecha(fechaModificada);
        Turno actualizado = turnoService.modificarTurno(t);
        assertNotEquals(actualizado, original);


    }
    /*@Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }*/
}