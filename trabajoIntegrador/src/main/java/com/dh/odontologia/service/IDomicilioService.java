package com.dh.odontologia.service;

import com.dh.odontologia.model.Domicilio;

import java.util.Set;

public interface IDomicilioService {
    void crearDomicilio(Domicilio domicilio);
    Domicilio leerDomicilio(Long id);
    void modificarDomicilio(Domicilio domicilio);
    void eliminarDomicilio(Long id);
    Set<Domicilio> listarDomicilios();
}
