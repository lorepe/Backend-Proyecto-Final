package com.dh.odontologia.service;

import org.junit.*;
import com.dh.odontologia.model.OdontologoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    @Test
    public void testCrearOdontologo(){
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Gloria");
        odontologoDTO.setApellido("Escobar Ruiz");
        odontologoDTO.setMatricula(60001);


        OdontologoDTO odontologoGloria = odontologoService.leerOdontologo(1L);
        assertTrue(odontologoGloria!= null);

    }
    public void cargarDataSet() {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Carga");
        odontologoDTO.setApellido("informacion");
        odontologoDTO.setMatricula(0000);
    }


    @Test
    public void eliminarOdontologoTest() {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Carga");
        odontologoDTO.setApellido("informacion");
        odontologoDTO.setMatricula(0000);
        Long id= odontologoDTO.getId();
        odontologoService.eliminarOdontologo(2L);
        Assert.assertTrue(odontologoService.leerOdontologo(2L)==null);

    }

    @Test
    public void traerTodos() {
        cargarDataSet();
        Set<OdontologoDTO> odontologosDTO = odontologoService.listarOdontologos();

        Assert.assertFalse(odontologosDTO.isEmpty());
        //Assert.assertTrue(odontologosDTO.size() >= 1);
        System.out.println(odontologosDTO);
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