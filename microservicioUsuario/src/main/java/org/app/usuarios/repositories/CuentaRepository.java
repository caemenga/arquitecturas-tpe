package org.app.usuarios.repositories;

import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("CuentaRepository")
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}
