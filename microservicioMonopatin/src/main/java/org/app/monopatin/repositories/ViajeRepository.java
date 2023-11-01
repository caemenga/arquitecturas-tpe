package org.app.monopatin.repositories;

import org.app.monopatin.entities.DTO.ReporteKmsDTO;
import org.app.monopatin.entities.DTO.ReporteKmsPausaDTO;
import org.app.monopatin.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v.monopatin.id AS idMonopatin, SUM(v.kilometros) AS kmsTotales " + " FROM Viaje v GROUP BY v.monopatin.id ORDER BY kmsTotales DESC")
    List<ReporteKmsDTO> getReporteKms();

    @Query("SELECT v.monopatin.id AS idMonopatin, SUM(v.kilometros) AS kmsTotales, SUM(v.pausa) AS tiempoPausa " + " FROM Viaje v GROUP BY v.monopatin.id ORDER BY kmsTotales DESC")
    List<ReporteKmsPausaDTO> getReporteKmsPausa();
}
