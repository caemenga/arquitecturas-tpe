package org.app.parada.repositories;

import org.app.parada.entities.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("ParadaRepository")
public interface ParadaRepository extends MongoRepository<Parada, String> {
}
