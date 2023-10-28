package org.app.usuarios.repositories;

import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CuentaRepository")
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
