package com.dh.odontologia.service;

import com.dh.odontologia.model.Domicilio;
import com.dh.odontologia.repository.IDomicilioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class DomicilioService implements IDomicilioService {
    @Autowired
    IDomicilioRepository domicilioRepository;
    @Autowired
    ObjectMapper mapper;

    private void guardarDomicilio(Domicilio domicilio){
        domicilioRepository.save(domicilio);
    }

    @Override
    public void crearDomicilio(Domicilio domicilio) {
        guardarDomicilio(domicilio);
    }

    @Override
    public Domicilio leerDomicilio(Long id) {
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        Domicilio domicilio1 = null;
        if (domicilio.isPresent()){
            domicilio1= mapper.convertValue(domicilio,Domicilio.class);
        }
        return domicilio1;
    }

    @Override
    public void modificarDomicilio(Domicilio domicilio) {
        guardarDomicilio(domicilio);
    }

    @Override
    public void eliminarDomicilio(Long id) {
        domicilioRepository.deleteById(id);
    }

    @Override
    public Set<Domicilio> listarDomicilios() {
        List<Domicilio> domicilios =domicilioRepository.findAll();
        Set<Domicilio> domiciliosList = new HashSet<>();
        for (Domicilio domicilio : domicilios) {
            domicilios.add(mapper.convertValue(domicilio,Domicilio.class));
        }
        return domiciliosList;
    }
}
