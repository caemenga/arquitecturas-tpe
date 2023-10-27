package org.app.mantenimiento.Repositories;

import org.app.mantenimiento.entities.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MantenimientoRepository")
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
}
