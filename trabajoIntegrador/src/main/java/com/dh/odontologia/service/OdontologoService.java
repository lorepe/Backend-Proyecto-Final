package com.dh.odontologia.service;

import com.dh.odontologia.model.Odontologo;
import com.dh.odontologia.model.OdontologoDTO;
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

    private void guardarOdontologo(OdontologoDTO odontologoDTO){
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
    }
    @Override
    public OdontologoDTO crearOdontologo(OdontologoDTO odontologoDTO) {
        guardarOdontologo(odontologoDTO);
        return odontologoDTO;
    }

    @Override
    public OdontologoDTO leerOdontologo(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        if (odontologo.isPresent()){
            odontologoDTO= mapper.convertValue(odontologo,OdontologoDTO.class);
        }
        return odontologoDTO;
    }

    @Override
    public void modificarOdontologo(OdontologoDTO odontologoDTO) {
        guardarOdontologo(odontologoDTO);

    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Set<OdontologoDTO> listarOdontologos() {
        List<Odontologo> odontologos =odontologoRepository.findAll();
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();
        for (Odontologo odontologo: odontologos) {
            odontologosDTO.add(mapper.convertValue(odontologo,OdontologoDTO.class));
        }
        return odontologosDTO;
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
