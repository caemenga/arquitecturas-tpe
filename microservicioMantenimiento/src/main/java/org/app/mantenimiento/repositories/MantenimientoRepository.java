package org.app.mantenimiento.repositories;

import org.app.mantenimiento.entities.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository("MantenimientoRepository")
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {

//    @Query("UPDATE Mantenimiento m SET m.finMantenimiento = ?1 WHERE m.id = ?2")
//    public abstract Optional<Mantenimiento> setFinMantenimiento( Date fecha, Long idMantenimiento);
}
