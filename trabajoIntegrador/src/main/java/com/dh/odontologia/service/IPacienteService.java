package com.dh.odontologia.service;

import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.dto.PacienteDTO;
import java.util.Collection;

public interface IPacienteService {
    PacienteDTO crearPaciente(PacienteDTO pacienteDTO);
    PacienteDTO leerPaciente(Long id);
    void modificarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundExceptions;
    void eliminarPaciente(Long id) throws ResourceNotFoundExceptions;
    Collection<PacienteDTO> listarPacientes();

    //Set<PacienteDTO> getPacientesByApellidoLike(String apellido);
}
