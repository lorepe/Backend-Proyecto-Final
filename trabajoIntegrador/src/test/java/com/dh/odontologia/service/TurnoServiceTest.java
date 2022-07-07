package com.dh.odontologia.service;

import com.dh.odontologia.model.*;
import com.dh.odontologia.model.dto.OdontologoDTO;
import com.dh.odontologia.model.dto.PacienteDTO;
import com.dh.odontologia.model.dto.TurnoDTO;
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
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    ObjectMapper mapper;
    @Test
    public void testCrearTurno(){
        TurnoDTO turnoDTO = new TurnoDTO();



        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Odontologo");
        odontologoDTO.setApellido("Apellido odont");
        odontologoDTO.setMatricula(11111);

        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        turnoDTO.setOdontologo(odontologo);

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNombre("Paciente");
        pacienteDTO.setApellido("Apellido paciente ");
        pacienteDTO.setDomicilio(new Domicilio("137A","103F","Suba","Bogota"));

        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        turnoDTO.setPaciente(paciente);

        Date fecha = new Date();
        turnoDTO.setFecha(fecha);

        PacienteDTO pacienteMilena = pacienteService.leerPaciente(1L);
        OdontologoDTO odontologoGloria = odontologoService.leerOdontologo(1L);
        TurnoDTO turnoDTO1= turnoService.leerTurno(1L);
        Assert.assertTrue(turnoDTO1.getId() == 1);
        Assert.assertTrue(turnoDTO1.getId() == 1);


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