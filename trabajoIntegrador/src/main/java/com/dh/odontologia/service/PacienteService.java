package com.dh.odontologia.service;


import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.Paciente;
import com.dh.odontologia.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements IPacienteService {
    @Autowired
    IPacienteRepository pacienteRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public Paciente crearPaciente(Paciente paciente) {
        guardarPaciente(paciente);
        return paciente;
    }

    @Override
    public Paciente leerPaciente(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        Paciente pacienteOp = null;
        if (paciente.isPresent()){
            pacienteOp = mapper.convertValue(paciente,Paciente.class);
        }
        return pacienteOp;
    }
    private  Paciente guardarPaciente(Paciente paciente){
        //Paciente paciente = mapper.convertValue(paciente,Paciente.class);
        return pacienteRepository.save(paciente);
    }

    @Override
    public void modificarPaciente(Paciente paciente) throws ResourceNotFoundExceptions {
       if (leerPaciente(paciente.getId())==null)
           throw new ResourceNotFoundExceptions("No existe un paciente con el id"+ paciente.getId());
        guardarPaciente(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundExceptions {
        if (leerPaciente(id)==null)
            throw  new ResourceNotFoundExceptions("No existe el paciente con el id:"+ id);

        pacienteRepository.deleteById(id);
    }

    @Override
    public Collection<Paciente> listarPacientes() {

        List<Paciente> pacientes =pacienteRepository.findAll();
        Set<Paciente> pacientesDTO = new HashSet<Paciente>();
        for (Paciente paciente: pacientes) {
            pacientesDTO.add(mapper.convertValue(paciente,Paciente.class));
        }

        return pacientesDTO;
    }

    /*@Override
    public Set<PacienteDTO> getPacientesByApellidoLike(String apellido) {
        Set<Paciente> allPacientes = pacienteRepository.getPacienteByApellidoLike(apellido);
        Set<PacienteDTO> allPacientesDTO = new HashSet<PacienteDTO>();
        for(Paciente paciente: allPacientes)
            allPacientesDTO.add(mapper.convertValue(paciente,PacienteDTO.class));

        return allPacientesDTO;
    }/*


    /*@Override
    public Set<PacienteDTO> getPacientesByApellidoLike(String apellido) {
        Set<Paciente> paciente = pacienteRepository.getPacienteByApellidoLike(apellido);
        Set<PacienteDTO> pacienteDTO = new HashSet<>();

            pacienteDTO.add(mapper.convertValue(paciente, PacienteDTO.class));

        return pacienteDTO;
    }*/


}
