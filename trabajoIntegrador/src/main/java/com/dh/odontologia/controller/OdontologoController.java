package com.dh.odontologia.controller;

import com.dh.odontologia.exceptions.BadRequestException;
import com.dh.odontologia.model.OdontologoDTO;
import com.dh.odontologia.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO)throws BadRequestException {
        odontologoService.crearOdontologo(odontologoDTO);

        logger.info("Se creo el odontologo: "+odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public OdontologoDTO getOdontologo(@PathVariable Long id){
        return  odontologoService.leerOdontologo(id);
    }
    @PutMapping
    public ResponseEntity<?> modificarOdontologo(@RequestBody OdontologoDTO odontologoDTO){
        odontologoService.modificarOdontologo(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo( @PathVariable Long id ){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping
    public Collection<OdontologoDTO> getTodosOdontologos(){
        return odontologoService.listarOdontologos();
    }
}
