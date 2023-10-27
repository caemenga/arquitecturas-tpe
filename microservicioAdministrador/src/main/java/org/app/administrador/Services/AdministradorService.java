package org.app.administrador.Services;

import org.app.administrador.Entities.Monopatin;
import org.app.administrador.Entities.Parada;
import org.app.administrador.Repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service("administrador")
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestTemplate monopatinRestTemplate;
    
    public boolean registrarMantenimiento(long idMonopatin) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Monopatin> response = monopatinRestTemplate.exchange(
                "http://localhost:8085/monopatines/" + idMonopatin,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Monopatin>() {}
        );
        if(response.getStatusCode().is2xxSuccessful()){
            Monopatin mono = response.getBody();
            if(mono.getEnMantenimiento().equals("false")){

            }
        }

//        ResponseEntity<String> respuesta = monopatinRestTemplate.exchange(
//                "http://localhost:8085/mantenimiento",
//                HttpMethod.POST,
//                reqEntity,
//                String.class
//        );
        if(respuesta != null) {
            return ResponseEntity.ok("Monopatin agregado con exito");
        }
    }

    public Object finMantenimiento(long idMantenimiento) {
    }

    public Object ubicarMonopatinEnParada(long idMonopatin) {
    }

    public Object addMonopatin(Monopatin monopatin) {
    }

    public Object eliminarMonopatin(long idMonopatin) {
    }

    public Object addParada(Parada parada) {
    }

    public Object deleteParada(long idParada) {
    }
    
    public ResponseEntity<?> definirPrecio(long tarifa){
        
    }

    public Object anularCuenta(long idCuenta) {
    }

    public List<Monopatin> reporteMonopatinesPorKM() {
    }

    public List<Monopatin> reporteMonopatinesPorPausas() {
    }

    public List<Monopatin> reporteMonopatinesSinPausas() {
    }
}
