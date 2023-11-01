package org.app.monopatin.services;


import org.app.monopatin.entities.DTO.MonopatinViajeDTO;
import org.app.monopatin.entities.Viaje;
import org.app.monopatin.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("viajeService")
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    public List<Viaje> getViajes() {
        return viajeRepository.findAll();
    }

    public Viaje addViaje(Viaje v) {
        return viajeRepository.save(v);
    }

    public Optional<Viaje> getById(Long id) {
        return viajeRepository.findById(id);
    }

    public List<MonopatinViajeDTO> findAllByAnio(Integer cant, Long anio) {
        List<MonopatinViajeDTO> l = viajeRepository.findAllByAnio(anio);
//        for(MonopatinViajeDTO m : l){
//            System.out.println("aaaaaaaaaaaaaaaaa");
//            System.out.println(m.toString());
//        }
        return l;
    }

    public boolean deleteViaje(Long id) throws Exception {
        try{
            if(viajeRepository.existsById(id)){
                viajeRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
