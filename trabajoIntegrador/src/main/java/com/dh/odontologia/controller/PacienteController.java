package com.dh.odontologia.controller;

import com.dh.odontologia.exceptions.BadRequestException;
import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.Odontologo;
import com.dh.odontologia.model.Paciente;
import com.dh.odontologia.service.IDomicilioService;
import com.dh.odontologia.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger =  Logger.getLogger(PacienteController.class);
    @Autowired
    IPacienteService pacienteService;
    @Autowired
    IDomicilioService domicilioService;

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    private ResponseEntity<?> exception(ResourceNotFoundExceptions ex, WebRequest request){
        logger.error(ex);
        return new ResponseEntity<>("Error manejado por Resource",HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<?> crearPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        if (paciente!= null){
            domicilioService.crearDomicilio(paciente.getDomicilio());
            pacienteService.crearPaciente(paciente);
            logger.info("Se creo el paciente "+paciente);
            return  ResponseEntity.ok("Paciente ("+paciente+ ") creado");
        }else{
            throw new BadRequestException("Error no se ingreso correctamente el paciente");
        }


    }

    @GetMapping("/{id}")
    public Paciente getPaciente(@PathVariable Long id) throws BadRequestException, ResourceNotFoundExceptions {
        if(pacienteService.leerPaciente(id)!=null) {
            if (id != null) {
                logger.info("Se busco a el paciente con id: " + id);
                return pacienteService.leerPaciente(id);
            } else {
                throw new BadRequestException("Error no se puede eliminar");
            }
        }else{
            throw new ResourceNotFoundExceptions("No existe el paciente con el id: "+id);
        }
    }

    @PutMapping
    public ResponseEntity<?> modificarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundExceptions, BadRequestException {
        if(pacienteService.leerPaciente(paciente.getId())!=null) {
            if (paciente.getId() != null) {
                Paciente paciente1 =pacienteService.modificarPaciente(paciente);
                logger.info("Se modifico el paciente con id: " + paciente1.getId());
                return ResponseEntity.ok("El paciente: (" + paciente + ") se modifico");
            } else {
                throw new BadRequestException("Error no se puede modificar");
            }
        }else{
            throw new ResourceNotFoundExceptions("No existe el paciente con el id: "+paciente.getId());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente( @PathVariable Long id ) throws ResourceNotFoundExceptions, BadRequestException {
        if(pacienteService.leerPaciente(id)!=null) {
            if (id != null) {
                pacienteService.eliminarPaciente(id);
                logger.info("Se elimino el paciente con id: " + id);
                return ResponseEntity.ok("Eliminado");
            } else {
                throw new BadRequestException("Error no se puede eliminar");
            }
        }else{
            throw new ResourceNotFoundExceptions("No existe el paciente con el id: "+id);
        }
    }
    @GetMapping
    public Collection<Paciente> getTodosPacientes(){
        logger.info("Listando los pacientes");
        return pacienteService.listarPacientes();
    }


}
