package com.dh.odontologia.service;

import com.dh.odontologia.model.Domicilio;
import com.dh.odontologia.model.dto.PacienteDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
* */
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Test
    public void testCrearPaciente(){
        Date fecha = new Date();
        PacienteDTO pacienteDTO = new PacienteDTO();

        pacienteDTO.setNombre("Prueba");
        pacienteDTO.setApellido("Creacion");
        pacienteDTO.setDni("0000");
        pacienteDTO.setFechaIngreso(fecha);
        Domicilio domicilio = new Domicilio("137","103","Bogota","bogota");
        pacienteDTO.setDomicilio(domicilio);
        //pacienteDTO.setNombre("Milena");
        //pacienteDTO.setApellido("Vasquez Escobar");

        pacienteService.crearPaciente(pacienteDTO);
        //Set<PacienteDTO> pacientes=pacienteService.getPacienteByApellido("Creacion");
        //PacienteDTO pacienteMilena = pacienteService.leerPaciente(1L);
        //assertTrue(pacientes.contains(pacienteDTO));
        //assertTrue(pacientes.size()>0);

    }

}