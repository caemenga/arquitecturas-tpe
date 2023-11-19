package org.app.mantenimiento.services;

import org.app.mantenimiento.entities.DTO.MantenimientoDTO;
import org.app.mantenimiento.entities.DTO.ReporteKmsDTO;
import org.app.mantenimiento.entities.DTO.ReporteKmsPausaDTO;
import org.app.mantenimiento.entities.Mantenimiento;
import org.app.mantenimiento.repositories.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service("mantenimiento")
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;
    @Autowired
    private HttpService http;

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientoRepository.findAll();
    }

    public Mantenimiento addMantenimiento(MantenimientoDTO idMonopatin) {
        Mantenimiento m = new Mantenimiento(idMonopatin.getIdMantenimiento());
        System.out.println(m);
        return mantenimientoRepository.save(m);
    }

    public Optional<Mantenimiento> getById(Long id) {
        return mantenimientoRepository.findById(id);
    }

    public boolean deleteMantenimiento(Long id) throws Exception {
        try {
            if (mantenimientoRepository.existsById(id)) {
                mantenimientoRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Mantenimiento finMantenimiento(MantenimientoDTO idMantenimiento) {
        Optional<Mantenimiento> m = mantenimientoRepository.findById(idMantenimiento.getIdMantenimiento());

        if (m.isPresent()) {
            long currentTimeMillis = System.currentTimeMillis();
            Date fecha = new Date(currentTimeMillis);
            m.get().setFinMantenimiento(fecha);
            return mantenimientoRepository.save(m.get());
        }
        return null;
    }

    public ResponseEntity<?> getReporteKms(String token, Boolean pausa) {

        String url = "/viajes/reporte/kms?pausa=" + pausa;
        ResponseEntity<?> response = this.http.getRequest(token, url);
//        RestTemplate rest = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//
//        System.out.println(url);

//        if (pausa) {
//            ResponseEntity<List<ReporteKmsPausaDTO>> response = rest.exchange(
//                    url,
//                    HttpMethod.GET,
//                    requestEntity,
//                    new ParameterizedTypeReference<>(){}
//            );
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            System.out.println(response.getBody());

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No hay reporte de monopatines.");
        }
//        } else {
//            ResponseEntity<List<ReporteKmsDTO>> response = rest.exchange(
//                    url,
//                    HttpMethod.GET,
//                    requestEntity,
//                    new ParameterizedTypeReference<>(){}
//            );
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            System.out.println(response.getBody());
//
//            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
//                return ResponseEntity.ok(response.getBody());
//            } else {
//                return ResponseEntity.ok("No hay reporte de monopatines.");
//            }
//        }
//    }
    }
}
