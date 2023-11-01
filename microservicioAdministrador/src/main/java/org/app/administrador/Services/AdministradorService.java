package org.app.administrador.Services;

import org.app.administrador.Entities.Cuenta;
import org.app.administrador.Entities.DTO.CuentaDTO;
import org.app.administrador.Entities.DTO.MantenimientoDTO;
import org.app.administrador.Entities.DTO.MonopatinDTO;
import org.app.administrador.Entities.Mantenimiento;
import org.app.administrador.Entities.Monopatin;
import org.app.administrador.Entities.Parada;
import org.app.administrador.Repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service("administrador")
public class AdministradorService {

//    @Autowired
//    private AdministradorRepository administradorRepository;
//    private WebCli monopatinClient;
//
//    @Autowired
//    private RestTemplate mantenimientoClienteRest;
//
//    //@Autowired
//    //private RestTemplate paradaRestTemplate;

    public ResponseEntity<String> registrarMantenimiento(MonopatinDTO idMonopatin) throws Exception{
        RestTemplate restMono = new RestTemplate();

        System.out.println(idMonopatin.toString());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        //traer monopatin
        String url = "http://localhost:8082/monopatines/" + idMonopatin.getId();
        System.out.println(url);

        ResponseEntity<Monopatin> response = restMono.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Monopatin>(){}
        );
        headers.setContentType(MediaType.APPLICATION_JSON);

        //si el monopatin es valido
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            Monopatin monopatin = response.getBody();
            System.out.println("mono: " + monopatin.toString());

            MantenimientoDTO m = new MantenimientoDTO(idMonopatin.getIdMonopatin());
            HttpEntity<MantenimientoDTO> requestMantenimiento = new HttpEntity<MantenimientoDTO>(m, headers);

            //si el monopatin no esta en mantenimiento
            if(!monopatin.isEnMantenimiento()) {
                System.out.println("reqMaN: " + requestMantenimiento.getBody());
                //crear fila y entidad en microserv mantenimiento
                ResponseEntity<Mantenimiento> resp = restMono.exchange(
                        "http://localhost:8085/mantenimiento",
                        HttpMethod.POST,
                        requestMantenimiento,
                        Mantenimiento.class
                );
                //si se creo correctamente
                if(resp.getStatusCode().is2xxSuccessful()) {
                    System.out.println("Vamos!!!!!");

                    //setear monopatin en mantenimiento = true
                    ResponseEntity<Monopatin> resp2 = restMono.exchange(
                            //monopatines/2/mantenimiento/true
                            "http://localhost:8082/monopatines/" + idMonopatin.getId() +"/mantenimiento/" + true,
                            HttpMethod.PUT,
                            requestEntity,
                            new ParameterizedTypeReference<Monopatin>() {}
                    );
                    return ResponseEntity.ok("Monopatin agregado a mantenimiento con exito");
                }
            }
        }
        return ResponseEntity.ok("agregado c/ exito");
    }

//    public ResponseEntity<?> finMantenimiento(long idMantenimiento) {
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<Mantenimiento> response = mantenimientoRestTemplate.exchange(
//                "http://localhost:8082/mantenimiento/" + idMantenimiento,
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<Mantenimiento>() {}
//        );
//
//        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
//            Mantenimiento m = response.getBody();
//            if(m.getFinMantenimiento()==null){
//                HttpEntity<Long> requestMantenimientoDTO = new HttpEntity<>(idMantenimiento, headers);
//                ResponseEntity<Mantenimiento> responseMantenimiento = mantenimientoRestTemplate.exchange(
//                        "http://localhost:8082/mantenimiento/",
//                        HttpMethod.PUT,
//                        requestMantenimientoDTO,
//                        new ParameterizedTypeReference<Mantenimiento>() {}
//                );
//                m.getMonopatinId()
//            }
//
//        }
//            m.getMonopatinId()
//        }
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

        RestTemplate r = new RestTemplate();
        ResponseEntity<Monopatin> response = r.exchange(
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

//    public ResponseEntity<?> eliminarMonopatin(long idMonopatin) {
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<Monopatin> response = monopatinRestTemplate.exchange(
//                "http://localhost:8082/monopatin",
//                HttpMethod.DELETE,
//                requestEntity,
//                new ParameterizedTypeReference<Monopatin>() {}
//        );
//        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
//            return ResponseEntity.ok(response.getBody());
//        } else {
//            return ResponseEntity.ok("No se ha podido eliminar el monopatin con exito");
//        }
//    }

//    public Object addParada(Parada parada) {
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<Parada> response = paradaRestTemplate.exchange(
//                "http://localhost:8083/paradas/" + parada.getId(),
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<Parada>() {}
//        );
//        Parada p = response.getBody();
//        if(response.getStatusCode().isError()){
//            ResponseEntity<Parada> responseParada = paradaRestTemplate.exchange(
//                    "http://localhost:8083/paradas",
//                    HttpMethod.POST,
//                    requestEntity,
//                    new ParameterizedTypeReference<Parada>() {}
//            );
//            if(responseParada.getStatusCode().is2xxSuccessful()){
//                return ResponseEntity.ok(responseParada.getBody());
//            }
//        }
//        return ResponseEntity.ok("No se ha podido crear la parada exitosamente");
//    }
//
//    public Object deleteParada(long idParada) {
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<Parada> responseParada = paradaRestTemplate.exchange(
//                "http://localhost:8083/paradas/" + idParada,
//                HttpMethod.DELETE,
//                requestEntity,
//                new ParameterizedTypeReference<Parada>() {}
//        );
//        if(responseParada.getStatusCode().is2xxSuccessful()){
//            return ResponseEntity.ok(responseParada.getBody());
//        }
//        return ResponseEntity.ok("No se ha podido eliminar la parada");
//    }
//
//    public ResponseEntity<?> definirPrecio(long tarifa){
//
//    }
//
   public ResponseEntity<?> anularCuenta(Long idCuenta) {
       //Traer Cuenta

        RestTemplate restCuenta = new RestTemplate();

       HttpHeaders headers = new HttpHeaders();
       HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

       String url = "http://localhost:8081/cuentas/" + idCuenta;
       System.out.println(url);

       ResponseEntity<Cuenta> response = restCuenta.exchange(
               url,
               HttpMethod.GET,
               requestEntity,
               new ParameterizedTypeReference<Cuenta>(){}
       );
       headers.setContentType(MediaType.APPLICATION_JSON);
       //si la cuenta existe


       if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
           ResponseEntity<Cuenta> response2 = restCuenta.exchange(

                   "http://localhost:8081/cuentas/anular/" + idCuenta,
                   HttpMethod.PUT,
                   requestEntity,
                   new ParameterizedTypeReference<Cuenta>() {
                   }
           );
           return ResponseEntity.ok("Monopatin agregado a mantenimiento con exito");
       }
       return ResponseEntity.ok("La cuenta no existe");
    }
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
