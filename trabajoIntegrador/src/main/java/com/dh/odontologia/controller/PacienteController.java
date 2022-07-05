package com.dh.odontologia.controller;

import com.dh.odontologia.model.PacienteDTO;
import com.dh.odontologia.service.IDomicilioService;
import com.dh.odontologia.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger =  Logger.getLogger(PacienteController.class);
    @Autowired
    IPacienteService pacienteService;
    @Autowired
    IDomicilioService domicilioService;
    @PostMapping
    public ResponseEntity<?> crearPaceinte(@RequestBody PacienteDTO pacienteDTO){
        domicilioService.crearDomicilio(pacienteDTO.getDomicilio());
        pacienteService.crearPaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public PacienteDTO getPaciente(@PathVariable Long id){
        return  pacienteService.leerPaciente(id);
    }

    @PutMapping
    public ResponseEntity<?> modificarPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacienteService.modificarPaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente( @PathVariable Long id ){
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping
    public Collection<PacienteDTO> getTodosPacientes(){
        return pacienteService.listarPacientes();
    }


}
