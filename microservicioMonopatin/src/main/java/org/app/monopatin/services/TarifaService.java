package org.app.monopatin.services;

import org.app.monopatin.entities.Tarifa;
import org.app.monopatin.repositories.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("tarifaService")
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public Optional<Tarifa> getById(Long id){

            return tarifaRepository.findById(id);
        }

}

