package org.app.monopatin.repositories;

import org.app.monopatin.entities.Tarifa;
import org.app.monopatin.entities.Viaje;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("tarifa")
public interface TarifaRepository extends JpaRepository<Tarifa, Long>{

    @Query(value ="SELECT t " +
            "FROM Tarifa t " +
            "ORDER BY t.fecha_caducacion DESC " +
            "LIMIT 1"
    )
    Optional<Tarifa> getUltimaTarifa();
}
