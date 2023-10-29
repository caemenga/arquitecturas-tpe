package org.app.administrador.Services;

import org.app.administrador.Entities.Mantenimiento;
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
    @Autowired
    private RestTemplate mantenimientoRestTemplate;
    @Autowired
    private RestTemplate paradaRestTemplate;
    
    public ResponseEntity<String> registrarMantenimiento(long idMonopatin) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        //traer monopatin para ver si existe
        ResponseEntity<Monopatin> response = monopatinRestTemplate.exchange(
                "http://localhost:8082/monopatines/" + idMonopatin,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Monopatin>() {}
        );

        //si el monopatin es valido
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            Monopatin monopatin = response.getBody();
            //si el monopatin no esta en mantenimiento
            if(monopatin.isEnMantenimiento()){
                //crear fila y entidad en microserv mantenimiento
                ResponseEntity<Mantenimiento> resp = mantenimientoRestTemplate.exchange(
                        "http://localhost:8085/mantenimiento/",
                        HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<Mantenimiento>() {}
                );
                //si se creo correctamente
                if(resp.getStatusCode().is2xxSuccessful()) {
                    //setear monopatin en mantenimiento = true
                    ResponseEntity<Monopatin> resp2 = monopatinRestTemplate.exchange(
                            //monopatines/2/mantenimiento/true
                            "http://localhost:8082/monopatines/" + idMonopatin +"/mantenimiento/" + true,
                            HttpMethod.PUT,
                            requestEntity,
                            new ParameterizedTypeReference<Monopatin>() {}
                    );
                    return ResponseEntity.ok("Monopatin agregado a mantenimiento con exito");
                }
            }
        }
        return ResponseEntity.ok("Monopatin no encontrado");
    }

//    public Object finMantenimiento(long idMantenimiento) {
//
//    }
//
//    public Parada ubicarMonopatinEnParada(long idMonopatin) {
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<Monopatin> responseMonopatin = monopatinRestTemplate.exchange(
//                "http://localhost:8082/monopatines/" + idMonopatin,
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<Monopatin>() {}
//        );
//        Monopatin monopatin = responseMonopatin.getBody();
//    }

    public ResponseEntity<?> addMonopatin(Monopatin monopatin) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Monopatin> response = monopatinRestTemplate.exchange(
                "http://localhost:8082/monopatin",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Monopatin>() {}
        );
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No se ha podido crear el monopatin con exito");
        }
    }

    public ResponseEntity<?> eliminarMonopatin(long idMonopatin) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Monopatin> response = monopatinRestTemplate.exchange(
                "http://localhost:8082/monopatin",
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Monopatin>() {}
        );
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No se ha podido eliminar el monopatin con exito");
        }
    }

    public Object addParada(Parada parada) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Parada> response = paradaRestTemplate.exchange(
                "http://localhost:8083/paradas/" + parada.getId(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Parada>() {}
        );
        Parada p = response.getBody();
        if(response.getStatusCode().isError()){
            ResponseEntity<Parada> responseParada = paradaRestTemplate.exchange(
                    "http://localhost:8083/paradas",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Parada>() {}
            );
            if(responseParada.getStatusCode().is2xxSuccessful()){
                return ResponseEntity.ok(responseParada.getBody());
            }
        }
        return ResponseEntity.ok("No se ha podido crear la parada exitosamente");
    }

    public Object deleteParada(long idParada) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Parada> responseParada = paradaRestTemplate.exchange(
                "http://localhost:8083/paradas/" + idParada,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Parada>() {}
        );
        if(responseParada.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.ok(responseParada.getBody());
        }
        return ResponseEntity.ok("No se ha podido eliminar la parada");
    }
//
//    public ResponseEntity<?> definirPrecio(long tarifa){
//
//    }
//
//    public Object anularCuenta(long idCuenta) {
//    }
//
//    public List<Monopatin> reporteMonopatinesPorKM() {
//    }
//
//    public List<Monopatin> reporteMonopatinesPorPausas() {
//    }
//
//    public List<Monopatin> reporteMonopatinesSinPausas() {
//    }
}
