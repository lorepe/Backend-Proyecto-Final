package com.dh.odontologia.service;

import com.dh.odontologia.model.Odontologo;

import java.util.Set;

public interface IOdontologoService {
    Odontologo crearOdontologo(Odontologo odontologo);
    Odontologo leerOdontologo(Long id);
    void modificarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);
    Set<Odontologo> listarOdontologos();
    //Set<OdontologoDTO> buscarOdontologoApellido(String lastname);
}
