package org.app.parada.services;

import org.app.parada.entities.Parada;
import org.app.parada.repositories.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("paradas")
public class ParadaService {
    @Autowired
    private ParadaRepository paradaRepository;

    public List<Parada> getParadas() {
        return paradaRepository.findAll();
    }

    public Parada addParada(Parada p) {
        return paradaRepository.save(p);
    }

    public Optional<Parada> getById(Long id) {
        return paradaRepository.findById(id);
    }

    public boolean deleteParada (Long id) throws Exception {
        try{
            if(paradaRepository.existsById(id)){
                paradaRepository.deleteById(id);
                return true;
            } else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
