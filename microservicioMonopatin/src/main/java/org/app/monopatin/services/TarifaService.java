package org.app.monopatin.services;

import org.app.monopatin.entities.Tarifa;
import org.app.monopatin.repositories.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("tarifaService")
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public Optional<Tarifa> getById(Long id) throws Exception {
        try{
            return tarifaRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Tarifa addTarifa(Tarifa tarifa) throws Exception {
        try{
            return tarifaRepository.save(tarifa);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Tarifa> getUltimaTarifa() throws Exception {
        try{
            return tarifaRepository.getUltimaTarifa();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Tarifa> getTarifas() {
        return this.tarifaRepository.findAll();
    }
}

