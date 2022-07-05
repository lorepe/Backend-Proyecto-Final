package com.dh.odontologia.service;


import com.dh.odontologia.model.Paciente;
import com.dh.odontologia.model.dto.PacienteDTO;
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
    public void crearPaciente(PacienteDTO pacienteDTO) {
        guardarPaciente(pacienteDTO);
    }

    @Override
    public PacienteDTO leerPaciente(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO = null;
        if (paciente.isPresent()){
            pacienteDTO = mapper.convertValue(paciente,PacienteDTO.class);
        }
        return pacienteDTO;
    }
    private void guardarPaciente(PacienteDTO pacienteDTO){
        Paciente paciente = mapper.convertValue(pacienteDTO,Paciente.class);
        pacienteRepository.save(paciente);
    }

    @Override
    public void modificarPaciente(PacienteDTO pacienteDTO) {
        guardarPaciente(pacienteDTO);
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Collection<PacienteDTO> listarPacientes() {
        List<Paciente> pacientes =pacienteRepository.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<PacienteDTO>();
        for (Paciente paciente: pacientes) {
            pacientesDTO.add(mapper.convertValue(paciente,PacienteDTO.class));
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
