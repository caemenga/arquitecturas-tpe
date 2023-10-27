package org.monopatin.Repositories;

import org.monopatin.entities.DTO.MonopatinDTO;
import org.monopatin.entities.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT new org.monopatin.entities.DTO.MonopatinDTO(m.id, m.enMantenimiento)" + " FROM Monopatin m WHERE m.id = :id")
    public abstract Optional<MonopatinDTO> getById(long id);

    @Query("UPDATE Monopatin m SET m.enMantenimiento = :bol WHERE m.id = :id")
    public abstract Optional<Monopatin> setearMantenimiento(long id, boolean bol);
}
