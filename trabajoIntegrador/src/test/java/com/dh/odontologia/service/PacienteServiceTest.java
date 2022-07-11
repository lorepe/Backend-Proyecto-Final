package com.dh.odontologia.service;

import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.Domicilio;
import com.dh.odontologia.model.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collection;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IDomicilioService domicilioService;


    public void cargarDataSet() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Carga");
        paciente.setApellido("informacion");
        Domicilio domicilio = new Domicilio("00","00","prueba","test");
        paciente.setDomicilio(domicilio);
        Date fecha = new Date();
        paciente.setFechaIngreso(fecha);
        domicilioService.crearDomicilio(domicilio);
        pacienteService.crearPaciente(paciente);
    }


    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundExceptions {
        cargarDataSet();
        pacienteService.eliminarPaciente(1L);
        Paciente paciente =pacienteService.leerPaciente(1L);
        assertTrue(paciente == null);

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
        cargarDataSet();
        Paciente paciente = new Paciente();
        paciente.setNombre("Carga");
        paciente.setApellido("informacion");
        Domicilio domicilio = new Domicilio("00","00","prueba","test");
        paciente.setDomicilio(domicilio);
        Date fecha = new Date();
        paciente.setFechaIngreso(fecha);
        domicilioService.crearDomicilio(domicilio);
        Paciente p = pacienteService.crearPaciente(paciente);
        Paciente original = pacienteService.leerPaciente(p.getId());
        p.setNombre("Modificado");
        Paciente actualizado=pacienteService.modificarPaciente(p);
        assertNotEquals(actualizado, original);


    }
    @Test
    public void crearPacienteTest (){
        cargarDataSet();

        Paciente paciente = new Paciente();
        paciente.setNombre("Prueba");
        paciente.setApellido("Crear");
        paciente.setDni("101245");
        Date fecha = new Date();
        paciente.setFechaIngreso(fecha);
        Paciente pacienteCreado = pacienteService.crearPaciente(paciente);
        assertNotNull(pacienteService.leerPaciente(pacienteCreado.getId()));

    }

}