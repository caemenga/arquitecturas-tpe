package org.app.monopatin.services;

import org.app.monopatin.entities.Tarifa;
import org.app.monopatin.repositories.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("tarifaService")
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public Optional<Tarifa> getById(Long id){

            return tarifaRepository.findById(id);
        }

    public Tarifa addTarifa(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    public Optional<Tarifa> getUltimaTarifa() {

        return tarifaRepository.getUltimaTarifa();
    }
}

