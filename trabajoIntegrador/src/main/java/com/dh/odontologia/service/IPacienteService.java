package com.dh.odontologia.service;

import com.dh.odontologia.exceptions.ResourceNotFoundExceptions;
import com.dh.odontologia.model.Paciente;
import java.util.Collection;

public interface IPacienteService {
    Paciente crearPaciente(Paciente paciente);
    Paciente leerPaciente(Long id);
    Paciente modificarPaciente(Paciente paciente) throws ResourceNotFoundExceptions;
    void eliminarPaciente(Long id) throws ResourceNotFoundExceptions;
    Collection<Paciente> listarPacientes();

    //Set<PacienteDTO> getPacientesByApellidoLike(String apellido);
}
