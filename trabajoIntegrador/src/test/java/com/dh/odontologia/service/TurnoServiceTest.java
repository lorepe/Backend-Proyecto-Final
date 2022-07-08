package com.dh.odontologia.service;

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

@SpringBootTest
class TurnoServiceTest {
    @Autowired
    private IPacienteService pacienteService;
    private Paciente paciente;

    @Autowired
    private IOdontologoService odontologoService;
    private Odontologo odontologo;
    private Domicilio domicilio;
    @Autowired
    private ITurnoService turnoService;
    @BeforeEach
    public void cargaDataSet() {
        paciente = new Paciente();
        paciente.setNombre("Pepe");
        paciente.setApellido("Pepardo");
        paciente.setDni("123456789");
        domicilio = new Domicilio();
        domicilio.setCalle("Calle Falsa");
        domicilio.setNumero("123");
        domicilio.setLocalidad("Springfield");
        domicilio.setProvincia("Springfield");
        paciente.setDomicilio(domicilio);

        odontologo = new Odontologo();
        odontologo.setNombre("Pepo");
        odontologo.setApellido("Pepardo");
        odontologo.setMatricula(123456);
    }

    @Test
    public void test01CrearTurnoConPacienteYOdontologoExistente() {

        Paciente pacienteCreado = pacienteService.crearPaciente(paciente);
        Odontologo odontologoCreado = odontologoService.crearOdontologo(odontologo);
        Turno turno = new Turno();
        Date date = new Date();
        turno.setFecha(date);
        turno.setPaciente(pacienteCreado);
        turno.setOdontologo(odontologoCreado);
        Turno turnoCreado = turnoService.crearTurno(turno);
        assertNotNull(turnoService.leerTurno(turnoCreado.getId()));
    }

   /*public void cargarDataSet() {
       Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
       PacienteDTO p = pacienteService.crearPaciente(new PacienteDTO("Santiago", "Paz", "88888888", new Date(), domicilio));
       this.odontologoService.registrarOdontologo(new Odontologo("Santiago", "Paz", 3455647));
       turnoService.registrarTurno(new Turno(pacienteService.buscar(1).get(),odontologoService.buscar(1).get(),new Date()));

   }
    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }*/
}