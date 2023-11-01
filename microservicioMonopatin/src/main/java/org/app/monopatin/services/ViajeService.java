package org.app.monopatin.services;


import org.app.monopatin.entities.DTO.ReporteKmsDTO;
import org.app.monopatin.entities.DTO.ReporteKmsPausaDTO;
import org.app.monopatin.entities.Viaje;
import org.app.monopatin.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("viajes")
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

    public List<ReporteKmsDTO> getReporteKms() {
        List<ReporteKmsDTO> reporte = viajeRepository.getReporteKms();
        for (ReporteKmsDTO r : reporte) {
            System.out.println("Reporte: " + r.getIdMonopatin() + " | " + r.getKmsTotales());
        }
        return reporte;
    }

    public List<ReporteKmsPausaDTO> getReporteKmsPausa() {
        List<ReporteKmsPausaDTO> reporte = viajeRepository.getReporteKmsPausa();
        for (ReporteKmsPausaDTO r : reporte) {
            System.out.println("Reporte: " + r.getIdMonopatin() + " | " + r.getKmsTotales() + " | " + r.getTiempoPausa());
        }
        return reporte;
    }
}
