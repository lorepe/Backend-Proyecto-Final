package com.dh.odontologia.service;

import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.Domicilio;
import com.dh.odontologia.model.Odontologo;
import com.dh.odontologia.model.Paciente;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void CrearPacienteTest (){
        Paciente paciente = new Paciente();
        paciente.setNombre("Gloria");
        paciente.setApellido("Escobar Ruiz");
        Domicilio domicilio = new Domicilio("137","103","Bogota","bogota");
        paciente.setDomicilio(domicilio);
        Date fecha = new Date();
        paciente.setFechaIngreso(fecha);

        pacienteService.crearPaciente(paciente);
        Paciente paciente1 = pacienteService.leerPaciente(1L);
        assertTrue(paciente1 != null);

    }
    public void cargarDataSet() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Carga");
        paciente.setApellido("informacion");
        Domicilio domicilio = new Domicilio("00","00","prueba","test");
        paciente.setDomicilio(domicilio);
        Date fecha = new Date();
        paciente.setFechaIngreso(fecha);
        pacienteService.crearPaciente(paciente);
    }


    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundExceptions {
        cargarDataSet();
        pacienteService.eliminarPaciente(1L);
        assertTrue(pacienteService.leerPaciente(1L) == null);

    }

    @Test
    public void traerTodosTest() {
        cargarDataSet();
        Collection<Paciente> pacientes = pacienteService.listarPacientes();

        assertTrue(!pacientes.isEmpty());
        assertTrue(pacientes.size() != 0);
        System.out.println(pacientes);
    }
    @Test
    public void editarPacieteTest() throws ResourceNotFoundExceptions {
        Paciente paciente = new Paciente();
        paciente.setNombre("Carga");
        paciente.setApellido("informacion");
        Domicilio domicilio = new Domicilio("00","00","prueba","test");
        paciente.setDomicilio(domicilio);
        Date fecha = new Date();
        paciente.setFechaIngreso(fecha);

        Paciente p = pacienteService.crearPaciente(paciente);
        Paciente original = pacienteService.leerPaciente(p.getId());
        p.setNombre("Pepito");
        pacienteService.modificarPaciente(p);
        assertNotEquals(actualizado, original);


    }

}