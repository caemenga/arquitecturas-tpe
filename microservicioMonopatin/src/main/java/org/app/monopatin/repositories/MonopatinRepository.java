package org.app.monopatin.repositories;

import org.app.monopatin.entities.DTO.MonopatinDTO;
import org.app.monopatin.entities.DTO.MonopatinViajeDTO;
import org.app.monopatin.entities.Monopatin;
import org.app.monopatin.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

   // @Query("SELECT new org.app.monopatin.entities.DTO.MonopatinDTO(m.id, m.enMantenimiento)" + " FROM Monopatin m WHERE m.id = :id")
   // public abstract Optional<MonopatinDTO> getById(long id);

    @Query("UPDATE Monopatin m SET m.enMantenimiento = :bol WHERE m.id = :id")
    public abstract Optional<Monopatin> setearMantenimiento(long id, boolean bol);

}
