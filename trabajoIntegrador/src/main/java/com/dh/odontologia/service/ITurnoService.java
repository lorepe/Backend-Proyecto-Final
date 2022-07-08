package com.dh.odontologia.service;

import com.dh.odontologia.model.Turno;

import java.util.Set;

public interface ITurnoService {
    Turno crearTurno(Turno turno);
    Turno leerTurno(Long id);
    void modificarTurno(Turno turno);
    void eliminarTurno(Long id);
    Set<Turno> listarTurnos();
}
