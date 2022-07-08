package com.dh.odontologia.service;

import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.Domicilio;
import com.dh.odontologia.model.Odontologo;
import com.dh.odontologia.model.Paciente;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private IOdontologoService odontologoService;
    @Test
    public void testCrearOdontologo(){
        Odontologo odontologo= new Odontologo();
        odontologo.setNombre("Gloria");
        odontologo.setApellido("Escobar Ruiz");
        odontologo.setMatricula(60001);

        odontologoService.crearOdontologo(odontologo);
        Odontologo odontologoGloria = odontologoService.leerOdontologo(1L);
        assertTrue(odontologoGloria != null);

    }
    public void cargarDataSet() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Carga");
        odontologo.setApellido("informacion");
        odontologo.setMatricula(0000);
        odontologoService.crearOdontologo(odontologo);
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