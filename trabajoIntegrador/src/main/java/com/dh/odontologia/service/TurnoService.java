package com.dh.odontologia.service;

import com.dh.odontologia.model.Turno;
import com.dh.odontologia.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService{
    @Autowired
    ITurnoRepository turnoRepository;
    @Autowired
    ObjectMapper mapper;

    private void guardarTurno(Turno turno){
        //Turno turno = mapper.convertValue(turno, Turno.class);
        turnoRepository.save(turno);
    }
    @Override
    public void crearTurno(Turno turno) {
        guardarTurno(turno);
    }

    @Override
    public Turno leerTurno(Long id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        Turno turnoOp = null;
        if (turno.isPresent()){
            turnoOp = mapper.convertValue(turno,Turno.class);
        }
        return turnoOp;
    }

    @Override
    public void modificarTurno(Turno turno) {
        guardarTurno(turno);
    }

    @Override
    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Set<Turno> listarTurnos() {
        List<Turno> turnos =turnoRepository.findAll();
        Set<Turno> turnosSet = new HashSet<>();
        for (Turno turno: turnos) {
            turnosSet.add(mapper.convertValue(turno,Turno.class));
        }
        return turnosSet;
    }
}
