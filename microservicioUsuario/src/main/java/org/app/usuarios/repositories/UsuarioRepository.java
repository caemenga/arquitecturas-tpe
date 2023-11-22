package org.app.usuarios.repositories;

import org.app.usuarios.entities.Authority;
import org.app.usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UsuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailIgnoreCase(@Param("email") String email);

    boolean existsUsersByEmailIgnoreCase(String email );

}
