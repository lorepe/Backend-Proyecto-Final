package com.dh.odontologia.service;

import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.Domicilio;
import com.dh.odontologia.model.Odontologo;
import com.dh.odontologia.model.Paciente;
import com.dh.odontologia.model.Turno;
import org.junit.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Set;


@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private IOdontologoService odontologoService;
    public void cargarDataSet() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Carga");
        odontologo.setApellido("informacion");
        odontologo.setMatricula(0000);
        odontologoService.crearOdontologo(odontologo);
    }

    @Test
    public void testCrearOdontologo(){
        cargarDataSet();
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Prueba");
        odontologo.setApellido("Creacion");
        odontologo.setMatricula(120345);
        Odontologo odontologoCreado = odontologoService.crearOdontologo(odontologo);
        assertNotNull(odontologoService.leerOdontologo(odontologoCreado.getId()));

    }
    @Test
    public void editarPacieteTest() throws ResourceNotFoundExceptions {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Carga");
        odontologo.setApellido("informacion");
        odontologo.setMatricula(0000);
        odontologoService.crearOdontologo(odontologo);

        Odontologo p = odontologoService.crearOdontologo(odontologo);
        Odontologo original = odontologoService.leerOdontologo(p.getId());
        p.setNombre("Pepito");
        Odontologo actualizado=odontologoService.modificarOdontologo(p);
        assertNotEquals(actualizado, original);


    }


    @Test
    public void eliminarOdontologoTest() {
        cargarDataSet();
        odontologoService.eliminarOdontologo(1L);
        assertTrue(odontologoService.leerOdontologo(1L) == null);

    }

    @Test
    public void traerTodosTest() {
        cargarDataSet();
        Set<Odontologo> odontologos = odontologoService.listarOdontologos();

        assertTrue(!odontologos.isEmpty());
        assertTrue(odontologos.size() >= 1);
        System.out.println(odontologos);
    }



   /* @Test
    public void buscarOdontologoApellido() {
        OdontologoDTO odontologo = new OdontologoDTO();
        odontologo.setApellido("pruebaLastName");
        odontologo.setNombre("pruebaName");

        odontologoService.crearOdontologo(odontologo);
        Set<OdontologoDTO> odontologos = odontologoService.buscarOdontologoApellido("prueba");

        boolean resultado = odontologos.size() > 0;

        assertTrue(resultado);
    }*/
}