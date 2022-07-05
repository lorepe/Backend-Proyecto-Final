package com.dh.odontologia.service;

import com.dh.odontologia.model.dto.PacienteDTO;
import java.util.Collection;

public interface IPacienteService {
    void crearPaciente(PacienteDTO pacienteDTO);
    PacienteDTO leerPaciente(Long id);
    void modificarPaciente(PacienteDTO pacienteDTO);
    void eliminarPaciente(Long id);
    Collection<PacienteDTO> listarPacientes();

    //Set<PacienteDTO> getPacientesByApellidoLike(String apellido);
}
