package com.dh.odontologia.repository;

import com.dh.odontologia.model.Odontologo;
import com.dh.odontologia.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
    /*@Query("from Pacientes p where p.apellido like %:apellido%")
    Set<Paciente> getPacienteByApellidoLike(@Param("apellido") String apellido);

     */
}
