package com.dh.odontologia.service;

import com.dh.odontologia.model.Odontologo;

import com.dh.odontologia.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class OdontologoService implements IOdontologoService {

    @Autowired
    IOdontologoRepository odontologoRepository;
    @Autowired
    ObjectMapper mapper;

    private Odontologo guardarOdontologo(Odontologo odontologo){
      //  Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        return odontologoRepository.save(odontologo);
    }
    @Override
    public Odontologo crearOdontologo(Odontologo odontologo) {
        guardarOdontologo(odontologo);
        return odontologo;
    }

    @Override
    public Odontologo leerOdontologo(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        Odontologo odontologoOp = null;
        if (odontologo.isPresent()){
            odontologoOp= mapper.convertValue(odontologo,Odontologo.class);
        }
        return odontologoOp;
    }

    @Override
    public Odontologo modificarOdontologo(Odontologo odontologo) {
        return guardarOdontologo(odontologo);

    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Set<Odontologo> listarOdontologos() {
        List<Odontologo> odontologos =odontologoRepository.findAll();
        Set<Odontologo> odontologosSet = new HashSet<>();
        for (Odontologo odontologo: odontologos) {
            odontologosSet.add(mapper.convertValue(odontologo,Odontologo.class));
        }
        return odontologosSet;
    }

   /* @Override
    public Set<OdontologoDTO> buscarOdontologoApellido(String apellido) {
        Set<Odontologo> odontologos = odontologoRepository.buscarOdontologoApellido(apellido);
        Set<OdontologoDTO> odontologosDTO = new HashSet<OdontologoDTO>();
        for(Odontologo odontologo: odontologos)
            odontologosDTO.add(mapper.convertValue(odontologo,OdontologoDTO.class));

        return odontologosDTO;
    }*/

}
