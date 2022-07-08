package com.dh.odontologia.controller;

import com.dh.odontologia.model.Turno;
import com.dh.odontologia.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger logger =  Logger.getLogger(TurnoController.class);
    @Autowired
    ITurnoService turnoService;
    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody Turno turno){
        turnoService.crearTurno(turno);
        logger.info("Se crea un turno: "+ turno);
        logger.debug("Se crea un turno: "+turno);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Turno getTurno(@PathVariable Long id){
        return  turnoService.leerTurno(id);
    }
    @PutMapping
    public ResponseEntity<?> modificarTurno(@RequestBody Turno turno){
        turnoService.modificarTurno(turno);
        logger.info("Se modifico el turno :"+turno);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno( @PathVariable Long id ){

        turnoService.eliminarTurno(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping
    public Collection<Turno> getTodosTurnos(){
        return turnoService.listarTurnos();
    }
}
