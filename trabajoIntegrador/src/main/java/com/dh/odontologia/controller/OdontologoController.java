package com.dh.odontologia.controller;

import com.dh.odontologia.exceptions.BadRequestException;
import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
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
    public ResponseEntity<?> crearOdontologo( @RequestBody Odontologo odontologo)throws BadRequestException {
        if (odontologo!= null){
            odontologoService.crearOdontologo(odontologo);
            logger.info("Se creo el odontologo: "+odontologo);
            return ResponseEntity.ok("Odontologo ("+odontologo+") creado");
        }else {
            throw new BadRequestException("Error no se ingreso correctamente el odontologo");
         }

    }

    @GetMapping("/{id}")
    public Odontologo getOdontologo(@PathVariable Long id) throws BadRequestException, ResourceNotFoundExceptions {
        if(odontologoService.leerOdontologo(id)!=null) {
            if (id != null) {
                logger.info("Se busco el odontologo con id " + id);
                return odontologoService.leerOdontologo(id);
            }else{
                throw new BadRequestException("Error no se puede eliminar");
            }
        }else{
            throw new ResourceNotFoundExceptions("No existe el odontologo con el id: "+id);
        }
    }
    @PutMapping
    public ResponseEntity<?> modificarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundExceptions, BadRequestException {
        if(odontologoService.leerOdontologo(odontologo.getId())!=null) {
            if (odontologo.getId() != null) {
                Odontologo odontologo1 = odontologoService.modificarOdontologo(odontologo);
                logger.info("Se modifico el odontologo con id: " + odontologo1.getId());
                return ResponseEntity.ok("El odontologo (" + odontologo + ") se modifico");
            }else{
                throw new BadRequestException("Error no se puede modificar");
            }
        }else {
            throw new ResourceNotFoundExceptions("No existe el odontologo con el id: "+odontologo.getId());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>eliminarOdontologo( @PathVariable Long id ) throws BadRequestException, ResourceNotFoundExceptions {
        if(odontologoService.leerOdontologo(id)!=null) {
            if (id != null) {
                odontologoService.eliminarOdontologo(id);
                logger.info("El odontologo con id: " + id + " se elimino");
                return ResponseEntity.ok("El odontologo con id: " + id + " se elimino");
            }else{
                throw new BadRequestException("Error no se puede eliminar");
            }
        }else{
            throw new ResourceNotFoundExceptions("No existe el odontologo con el id: "+id);
        }
    }

    @GetMapping
    public Collection<Odontologo> getTodosOdontologos(){
        logger.info("Se estan listando los odontologos");
        return odontologoService.listarOdontologos();
    }
}
