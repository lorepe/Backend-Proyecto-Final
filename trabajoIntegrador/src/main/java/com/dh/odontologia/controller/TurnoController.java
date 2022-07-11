package com.dh.odontologia.controller;

import com.dh.odontologia.exceptions.BadRequestException;
import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
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
    public ResponseEntity<?> crearTurno(@RequestBody Turno turno) throws BadRequestException {
        if (turno!= null) {
            turnoService.crearTurno(turno);
            logger.info("Se crea un turno: " + turno);
            return ResponseEntity.ok(HttpStatus.OK);
        }else{
            throw new BadRequestException("Error no se ingreso correctamente el turno");
        }
    }

    @GetMapping("/{id}")
    public Turno getTurno(@PathVariable Long id) throws BadRequestException, ResourceNotFoundExceptions {
        if(turnoService.leerTurno(id)!=null) {
            if (id != null) {
                logger.info("Se busco a el tueno con id: "+id);
                return turnoService.leerTurno(id);
            }else{
                throw new BadRequestException("Error no se puede eliminar");
            }
        }else{
            throw new ResourceNotFoundExceptions("No existe el turno con el id: "+id);
        }
    }
    @PutMapping
    public ResponseEntity<?> modificarTurno(@RequestBody Turno turno) throws BadRequestException, ResourceNotFoundExceptions {
        if(turnoService.leerTurno(turno.getId())!=null) {
            if (turno.getId() != null) {
                Turno turno1=turnoService.modificarTurno(turno);
                logger.info("Se modifico el turno conel id:" + turno1.getId());
                return ResponseEntity.ok("El turno: ("+turno+") se modifico");
            }else{
                throw new BadRequestException("Error no se puede modificar");
            }
        }else{
            throw new ResourceNotFoundExceptions("No existe el turno con el id: "+turno.getId());
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno( @PathVariable Long id ) throws BadRequestException, ResourceNotFoundExceptions {
        if(turnoService.leerTurno(id)!=null) {
            if (id != null) {
                turnoService.eliminarTurno(id);
                logger.info("Se elimino el turno con id: " + id);
                return ResponseEntity.ok("Eliminado");
            }else{
                throw new BadRequestException("Error no se puede eliminar");
            }
        }else {
            throw new ResourceNotFoundExceptions("No existe el turno con el id: "+id);
        }
    }
    @GetMapping
    public Collection<Turno> getTodosTurnos(){
        logger.info("Listando los turnos");
        return turnoService.listarTurnos();
    }
}
