package org.app.monopatin.repositories;

import org.app.monopatin.entities.DTO.MonopatinViajeDTO;
import org.app.monopatin.entities.DTO.ReporteKmsDTO;
import org.app.monopatin.entities.DTO.ReporteKmsPausaDTO;
import org.app.monopatin.entities.DTO.ReporteTotalFacturadoDTO;
import org.app.monopatin.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository("viaje")
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v.monopatin.id AS idMonopatin, SUM(v.kilometros) AS kmsTotales " + " FROM Viaje v GROUP BY v.monopatin.id ORDER BY kmsTotales DESC")
    List<ReporteKmsDTO> getReporteKms();

    @Query("SELECT v.monopatin.id AS idMonopatin, SUM(v.kilometros) AS kmsTotales, SUM(v.pausa) AS tiempoPausa " + " FROM Viaje v GROUP BY v.monopatin.id ORDER BY kmsTotales DESC")
    List<ReporteKmsPausaDTO> getReporteKmsPausa();

    @Query("SELECT v.monopatin.id AS idMonopatin, COUNT(v) AS totalViajes " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fechaHoraFin) = :anio " +
            "GROUP BY v.monopatin.id " +
            "HAVING COUNT(v) >= :cant")
    List<MonopatinViajeDTO> findAllByAnio(Long cant, Long anio);

   @Query("SELECT SUM(v.valorViaje) AS valorViaje " +
           "FROM Viaje v " +
           "WHERE YEAR(v.fechaHoraFin) = :anio AND MONTH(v.fechaHoraFin)>=:mes1 AND MONTH(v.fechaHoraFin)<=:mes2")
   ReporteTotalFacturadoDTO getReporteTotalFacturado(Long mes1, Long mes2, Long anio);

//    @Query(value =
//            "SELECT COUNT(id) as cantViajes, id_monopatin as idMonopatin " +
//                    "FROM microservicioMonopatin.viaje WHERE YEAR(fecha_hora_fin) = 2023 " +
//                    "GROUP BY id_monopatin " +
//                    "HAVING cantViajes >= 1", nativeQuery = true)


}
