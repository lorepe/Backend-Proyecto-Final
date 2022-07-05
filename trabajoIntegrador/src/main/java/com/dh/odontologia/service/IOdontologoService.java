package com.dh.odontologia.service;

import com.dh.odontologia.model.dto.OdontologoDTO;

import java.util.Set;

public interface IOdontologoService {
    OdontologoDTO crearOdontologo(OdontologoDTO odontologoDTO);
    OdontologoDTO leerOdontologo(Long id);
    void modificarOdontologo(OdontologoDTO odontologoDTO);
    void eliminarOdontologo(Long id);
    Set<OdontologoDTO> listarOdontologos();
    //Set<OdontologoDTO> buscarOdontologoApellido(String lastname);
}
