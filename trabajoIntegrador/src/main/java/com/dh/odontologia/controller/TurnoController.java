package com.dh.odontologia.controller;

import com.dh.odontologia.model.dto.TurnoDTO;
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
    public ResponseEntity<?> crearTurno(@RequestBody TurnoDTO turnoDTO){
        turnoService.crearTurno(turnoDTO);
        logger.info("Se crea un turno: "+ turnoDTO);
        logger.debug("Se crea un turno: "+turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TurnoDTO getTurno(@PathVariable Long id){
        return  turnoService.leerTurno(id);
    }
    @PutMapping
    public ResponseEntity<?> modificarTurno(@RequestBody TurnoDTO turnoDTO){
        turnoService.modificarTurno(turnoDTO);
        logger.info("Se modifico el turno :"+turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno( @PathVariable Long id ){

        turnoService.eliminarTurno(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping
    public Collection<TurnoDTO> getTodosTurnos(){
        return turnoService.listarTurnos();
    }
}
