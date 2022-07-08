package com.dh.odontologia.controller;

import com.dh.odontologia.exceptions.BadRequestException;
import com.dh.odontologia.model.Odontologo;
import com.dh.odontologia.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger logger =  Logger.getLogger(OdontologoController.class);
    @Autowired
    IOdontologoService odontologoService;
    @PostMapping
    public ResponseEntity<?> crearOdontologo(@RequestBody Odontologo odontologo)throws BadRequestException {
        odontologoService.crearOdontologo(odontologo);

        logger.info("Se creo el odontologo: "+odontologo);
        return ResponseEntity.ok("Odontologo creado");
    }

    @GetMapping("/{id}")
    public Odontologo getOdontologo(@PathVariable Long id){
        return  odontologoService.leerOdontologo(id);
    }
    @PutMapping
    public ResponseEntity<?> modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificarOdontologo(odontologo);
        return ResponseEntity.ok("El odontologo ("+odontologo+") se modifico");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo( @PathVariable Long id ){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("El odontologo con id: "+id+" se elimino");
    }
    @GetMapping
    public Collection<Odontologo> getTodosOdontologos(){
        logger.info("Se estan listando los odontologos");
        return odontologoService.listarOdontologos();
    }
}
