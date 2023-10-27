package org.app.paradas.services;

import org.app.paradas.entities.Parada;
import org.app.paradas.repositories.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paradas")
public class ParadaService {
    @Autowired
    private ParadaRepository paradaRepository;

    public Parada addParada(Parada p) {
        return paradaRepository.save(p);
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
