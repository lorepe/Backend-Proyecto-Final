package com.dh.odontologia.service;

import com.dh.odontologia.model.OdontologoDTO;
import com.dh.odontologia.model.PacienteDTO;
import java.util.Collection;
import java.util.Set;

public interface IPacienteService {
    void crearPaciente(PacienteDTO pacienteDTO);
    PacienteDTO leerPaciente(Long id);
    void modificarPaciente(PacienteDTO pacienteDTO);
    void eliminarPaciente(Long id);
    Collection<PacienteDTO> listarPacientes();

    //Set<PacienteDTO> getPacientesByApellidoLike(String apellido);
}
