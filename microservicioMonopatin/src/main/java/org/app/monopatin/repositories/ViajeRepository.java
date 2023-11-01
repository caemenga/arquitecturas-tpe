package org.app.monopatin.repositories;

import org.app.monopatin.entities.DTO.MonopatinViajeDTO;
import org.app.monopatin.entities.DTO.ReporteKmsDTO;
import org.app.monopatin.entities.DTO.ReporteKmsPausaDTO;
import org.app.monopatin.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository("ViajeRepository")
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
//    @Query("SELECT count(v.id), v.monopatin.id " +
//            "FROM Viaje v " +
//            "WHERE EXTRACT(year FROM v.fechaHoraFin) = :anio " +
//            "GROUP BY v.monopatin.id " +
//            "HAVING count(v.id)>=1 ")
    @Query(value =
            "SELECT COUNT(id) as cantViajes, id_monopatin as idMonopatin " +
            "FROM microservicioMonopatin.viaje WHERE YEAR(fecha_hora_fin) = 2023 " +
            "GROUP BY id_monopatin " +
            "HAVING cantViajes >= 1", nativeQuery = true)
    public abstract List<MonopatinViajeDTO> findAllByAnio(Long anio);

    @Query("SELECT v.monopatin.id AS idMonopatin, SUM(v.kilometros) AS kmsTotales " + " FROM Viaje v GROUP BY v.monopatin.id ORDER BY kmsTotales DESC")
    List<ReporteKmsDTO> getReporteKms();

    @Query("SELECT v.monopatin.id AS idMonopatin, SUM(v.kilometros) AS kmsTotales, SUM(v.pausa) AS tiempoPausa " + " FROM Viaje v GROUP BY v.monopatin.id ORDER BY kmsTotales DESC")
    List<ReporteKmsPausaDTO> getReporteKmsPausa();
}
