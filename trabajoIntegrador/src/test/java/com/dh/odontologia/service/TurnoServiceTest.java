package com.dh.odontologia.service;

import com.dh.odontologia.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class TurnoServiceTest {
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private IPacienteService pacienteService;
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

        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        turnoDTO.setPaciente(paciente);

        Date fecha = new Date();
        turnoDTO.setFecha(fecha);

        PacienteDTO pacienteMilena = pacienteService.leerPaciente(1L);
        OdontologoDTO odontologoGloria = odontologoService.leerOdontologo(1L);
        TurnoDTO turnoDTO1= turnoService.leerTurno(1L);
        //assertTrue(turnoDTO1!= null);

    }
}