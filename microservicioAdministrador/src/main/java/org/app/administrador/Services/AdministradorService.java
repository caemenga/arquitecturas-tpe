package org.app.administrador.Services;

import org.apache.coyote.Response;
import org.app.administrador.Entities.*;
import org.app.administrador.Entities.DTO.*;
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

    @Autowired
    private HttpService http;

    public ResponseEntity<String> registrarMantenimiento(String token, MonopatinDTO idMonopatin) throws Exception{
        //String url = "/monopatines";

        //RestTemplate restMono = new RestTemplate();

        System.out.println(idMonopatin.toString());
        //HttpHeaders headers = new HttpHeaders();
        //HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        //traer monopatin
        String url = "http://localhost:8082/monopatines/" + idMonopatin.getId();
        ResponseEntity<?> response = this.http.getRequest(token, url);
//        System.out.println(url);
//
//        ResponseEntity<Monopatin> response = restMono.exchange(
//                url,
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<Monopatin>(){}
//        );
//        headers.setContentType(MediaType.APPLICATION_JSON);

        //si el monopatin es valido
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            Monopatin monopatin = (Monopatin) response.getBody();
            System.out.println("mono: " + monopatin.toString());


            MantenimientoDTO m = new MantenimientoDTO(idMonopatin.getIdMonopatin());

            String url2 = "/mantenimiento";

            //HttpEntity<MantenimientoDTO> requestMantenimiento = new HttpEntity<MantenimientoDTO>(m, headers);

            //si el monopatin no esta en mantenimiento
            if(!monopatin.isEnMantenimiento()) {
                //System.out.println("reqMaN: " + requestMantenimiento.getBody());
                //crear fila y entidad en microserv mantenimiento

                ResponseEntity<?> resp = this.http.postRequest(token, url2, m);
//                ResponseEntity<Mantenimiento> resp = restMono.exchange(
//                        "http://localhost:8085/mantenimiento",
//                        HttpMethod.POST,
//                        requestMantenimiento,
//                        Mantenimiento.class
//                );
                //si se creo correctamente
                if(resp.getStatusCode().is2xxSuccessful()) {
                    System.out.println("Vamos!!!!!");
                    String url3 = "monopatines/" + idMonopatin.getId() + "/mantenimiento/" + true;
                    ResponseEntity<?> resp2 = this.http.putRequest(token,url3);
                    //setear monopatin en mantenimiento = true
//                    ResponseEntity<Monopatin> resp2 = restMono.exchange(
//                            //monopatines/2/mantenimiento/true
//                            "http://localhost:8082/monopatines/" + idMonopatin.getId() +"/mantenimiento/" + true,
//                            HttpMethod.PUT,
//                            requestEntity,
//                            new ParameterizedTypeReference<Monopatin>() {}
//                    );
                    return ResponseEntity.ok("Monopatin agregado a mantenimiento con exito");
                }
            }
        }
        return ResponseEntity.ok("agregado c/ exito");
    }

    public ResponseEntity<?> addMonopatin(String token, Monopatin monopatin) {

        String url = "/monopatin";
        ResponseEntity<?> response = this.http.postRequest(token, url, monopatin);
//
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//
//        RestTemplate r = new RestTemplate();
//        ResponseEntity<Monopatin> response = r.exchange(
//                "http://localhost:8082/monopatin",
//                HttpMethod.POST,
//                requestEntity,
//                new ParameterizedTypeReference<Monopatin>() {}
//        );

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No se ha podido crear el monopatin con exito");
        }
    }

   public ResponseEntity<?> anularCuenta(String token, Long idCuenta) {
       //Traer Cuenta
//
//       RestTemplate restCuenta = new RestTemplate();
//
//       HttpHeaders headers = new HttpHeaders();
//       HttpEntity<Void> requestEntity = new HttpEntity<>(headers);



       String url = "/cuentas/" + idCuenta;
//       System.out.println(url);
       ResponseEntity<?> response = this.http.getRequest(token, url);
//       ResponseEntity<Cuenta> response = restCuenta.exchange(
//               url,
//               HttpMethod.GET,
//               requestEntity,
//               new ParameterizedTypeReference<Cuenta>(){}
//       );
//       headers.setContentType(MediaType.APPLICATION_JSON);
       //si la cuenta existe

       if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
           String url2 = "/cuentas/anular/" + idCuenta;

           this.http.putRequest(token,url2);

//           ResponseEntity<Cuenta> response2 = restCuenta.exchange(
//
//                   "http://localhost:8081/cuentas/anular/" + idCuenta,
//                   HttpMethod.PUT,
//                   requestEntity,
//                   new ParameterizedTypeReference<Cuenta>() {
//                   }
//           );
           return ResponseEntity.ok("Cuenta id: " + idCuenta + " anulada exitosamente");
       }
       return ResponseEntity.ok("La cuenta no existe");
    }


    public ResponseEntity<?> getReporteTotalFacturado(String token, Long mes1, Long mes2, Long anio) {
//        RestTemplate rest = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        //?mes1=1&mes2=12&anio=2023
        String url = "/viajes/reporte/valores?mes1="+mes1+"&mes2="+mes2+"&anio="+anio;
        ResponseEntity<ReporteTotalFacturadoDTO> response = (ResponseEntity<ReporteTotalFacturadoDTO>) this.http.getRequest(token, url);
//        ResponseEntity<ReporteTotalFacturadoDTO> response = rest.exchange(
//                url,
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<ReporteTotalFacturadoDTO>(){}
//        );
//        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println(response.getBody().getValorViaje());

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No se ha conseguido el reporte de la facturación con éxito.");
        }
    }

    public ResponseEntity<?> definirPrecio(String token, Tarifa t){

//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//
//        RestTemplate rUltimaTarifa = new RestTemplate();
//        //traigo la ultima tarifa
//        ResponseEntity<Tarifa> responseUltima = rUltimaTarifa.exchange(
//                "http://localhost:8082/tarifas/ultima",
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<Tarifa>() {}
//        );

        String url = "/tarifas/ultima";
        ResponseEntity<?> response = this.http.getRequest(token, url);

        //Si existe la tarifa
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            Tarifa ultimaTarifa = (Tarifa) response.getBody();

            //pregunto si las fechas son validas
            if(t.getFecha_creacion().after(ultimaTarifa.getFecha_caducacion())){

                String url2 = "/tarifas";
                ResponseEntity<?> response2 = this.http.postRequest(token, url, t);

//                HttpEntity<Tarifa> requestTarifa = new HttpEntity<>(t, headers);
//                RestTemplate r = new RestTemplate();
//
//                ResponseEntity<Tarifa> response = r.exchange(
//                        "http://localhost:8082/tarifas",
//                        HttpMethod.POST,
//                        requestTarifa,
//                        new ParameterizedTypeReference<Tarifa>() {}
//                );
                if(response2.getStatusCode().is2xxSuccessful() && response.getBody() != null){
                    return ResponseEntity.ok(response.getBody());
                } else {
                    return ResponseEntity.ok("No se ha podido crear la tarifa con exito");
                }
            }
            return ResponseEntity.ok("La fecha de inicio de la tarifa no es valida");
        }
         return ResponseEntity.ok("No se ha podido crear la tarifa con exito");
    }

    public ResponseEntity<?> getMonopatinesPorXViajes(String token, Long cant, Long anio) {

        String url = "/monopatines/viajes?cant=" + cant + "&anio=" + anio;

        ResponseEntity<List<MonopatinViajeDTO>> response = (ResponseEntity<List<MonopatinViajeDTO>>) this.http.getRequest(token, url);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No hay reporte de monopatines.");
        }
    }

    public ResponseEntity<?> getReporteEnOperacion(String token) {

        String url = "/monopatines/reporte/operacion";

        ResponseEntity<?> response = this.http.getRequest(token, url);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No hay reporte de monopatines.");
        }
    }

}
