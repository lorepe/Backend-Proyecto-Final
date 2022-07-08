package com.dh.odontologia.service;

import com.dh.odontologia.model.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@SpringBootTest
class TurnoServiceTest {
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private IPacienteService pacienteService;

    @Test
    public void testCrearTurno(){
        Turno turno = new Turno();

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Odontologo");
        odontologo.setApellido("Apellido odont");
        odontologo.setMatricula(11111);
        odontologoService.crearOdontologo(odontologo);
         turno.setOdontologo(odontologo);

        Paciente paciente = new Paciente();
        paciente.setNombre("Paciente");
        paciente.setApellido("Apellido paciente ");
        paciente.setDomicilio(new Domicilio("137A","103F","Suba","Bogota"));
        Date fecha1 = new Date();
        paciente.setFechaIngreso(fecha1);
        pacienteService.crearPaciente(paciente);
        turno.setPaciente(paciente);

        Date fecha = new Date();
        turno.setFecha(fecha);
        turnoService.crearTurno(turno);

        Paciente pacienteMilena = pacienteService.leerPaciente(1L);
        Odontologo odontologoGloria = odontologoService.leerOdontologo(1L);
        Turno turnoRecibido = turnoService.leerTurno(1L);
        Assert.assertTrue(turnoRecibido != null );
        Assert.assertTrue(pacienteMilena != null );
        Assert.assertTrue(odontologoGloria != null );



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