package com.dh.odontologia.controller;

import com.dh.odontologia.exceptions.BadRequestException;
import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.dto.PacienteDTO;
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
    public ResponseEntity<?> crearPaciente(@RequestBody PacienteDTO pacienteDTO) throws BadRequestException {
        if (pacienteDTO!= null){
            domicilioService.crearDomicilio(pacienteDTO.getDomicilio());
            pacienteService.crearPaciente(pacienteDTO);
        }else{
            String mensajeError= "Ingrese un paciente";
            throw new BadRequestException(mensajeError);
        }

        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public PacienteDTO getPaciente(@PathVariable Long id){
        return  pacienteService.leerPaciente(id);
    }

    @PutMapping
    public ResponseEntity<?> modificarPaciente(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundExceptions {
        pacienteService.modificarPaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente( @PathVariable Long id )throws ResourceNotFoundExceptions{

        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Eliminado");
    }
    @GetMapping
    public Collection<PacienteDTO> getTodosPacientes(){
        return pacienteService.listarPacientes();
    }


}
