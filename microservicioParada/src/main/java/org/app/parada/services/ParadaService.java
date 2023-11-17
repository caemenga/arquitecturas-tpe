package org.app.parada.services;

import org.app.parada.entities.Parada;
import org.app.parada.repositories.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.app.parada.utils.HelperHaversine.haversine;

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

    public Optional<Parada> getById(String id) {
        return paradaRepository.findById(id);
    }

    public boolean deleteParada (String id) throws Exception {
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

    public Optional<Parada> getParadaCercana(double latitud, double longitud) throws Exception {
        try{
            List<Parada> paradas = this.getParadas();

            Parada paradaMasCercana = paradas.get(0);
            double distanciaMenor = 999999999;

            for(Parada p : paradas){
                // Calcular la distancia haversine
                double distancia = haversine(latitud, longitud, p.getLatitud(), p.getLongitud());
                if(distancia<distanciaMenor){
                    distanciaMenor = distancia;
                    paradaMasCercana = p;
                }
                System.out.println("dist: " + distancia);
            }
            return Optional.of(paradaMasCercana);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
