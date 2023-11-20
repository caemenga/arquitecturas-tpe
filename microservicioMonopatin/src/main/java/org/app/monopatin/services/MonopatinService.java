package org.app.monopatin.services;


import org.app.monopatin.entities.DTO.ReporteMonopatin;

import org.app.monopatin.entities.DTO.MonopatinViajeDTO;

import org.app.monopatin.entities.Monopatin;
import org.app.monopatin.repositories.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("monopatineService")
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;
    @Autowired
    private ViajeService viajeService;

    public List<Monopatin> getMonopatines() throws Exception {
        try{
            return monopatinRepository.findAll();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Monopatin addMonopatin(Monopatin m) throws Exception {
        try{
            return monopatinRepository.save(m);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
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

                    if(!m.isEnMantenimiento()){
                        m.setParadaEstacionamientoId(null);
                    }

                    m.setEnMantenimiento(bol);
                    return Optional.of(monopatinRepository.save(m));
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return Optional.empty();
    }


    public Optional<ReporteMonopatin> reporteEnOperacion() throws Exception {
        try {
            return monopatinRepository.getReporteEnOperacion();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MonopatinViajeDTO> getMonopatinesPorXViajes(Long cant, Long anio) throws Exception {
        try {
            return viajeService.findAllByAnio(cant, anio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Monopatin> getMonopatinesPorParada(Long id) throws Exception {
        try {
            return monopatinRepository.getMonopatinesPorParada(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
