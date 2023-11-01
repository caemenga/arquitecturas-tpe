package org.app.monopatin.repositories;

import org.app.monopatin.entities.DTO.MonopatinViajeDTO;
import org.app.monopatin.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    @Query("SELECT new org.app.monopatin.entities.DTO.MonopatinViajeDTO(count(v), v.monopatin) " +
            "FROM Viaje v " +
            "WHERE v.fechaHoraFin = :anio " +
            "GROUP BY v.monopatin")
    List<MonopatinViajeDTO> findAllByAnio(Long anio);
}
