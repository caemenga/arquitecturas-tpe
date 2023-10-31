package org.app.monopatin.services;

import org.app.monopatin.entities.Monopatin;
import org.app.monopatin.repositories.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("monopatines")
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;

    public List<Monopatin> getMonopatines() {
        return monopatinRepository.findAll();
    }

    public Monopatin addMonopatin(Monopatin m) {
        //if(!monopatinRepository.existsById(m.getId())){
            return monopatinRepository.save(m);
            //return null;
       // }
        //return null;
    }

    public Optional<Monopatin> getById(Long id) {
        return monopatinRepository.findById(id);
    }

    public boolean deleteMonopatin (Long id) throws Exception {
        try{
            if(monopatinRepository.existsById(id)){
                monopatinRepository.deleteById(id);
                return true;
            } else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Monopatin> setearMantenimiento(Long id, boolean bol) throws Exception {
        try {
            if (monopatinRepository.existsById(id)) {
                Monopatin m = monopatinRepository.getById(id);
                if (m.isEnMantenimiento() != bol) {
                    m.setEnMantenimiento(bol);
                    return Optional.of(monopatinRepository.save(m));
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return Optional.empty();
    }
}
