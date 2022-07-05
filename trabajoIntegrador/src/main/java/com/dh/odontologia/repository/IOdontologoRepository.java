package com.dh.odontologia.repository;

import com.dh.odontologia.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Long> {
   // @Query("from Odontologos o where o.apellido like %:apellido%")
   // Set<Odontologo> buscarOdontologoApellido(@Param("apellido") String apellido);
}
