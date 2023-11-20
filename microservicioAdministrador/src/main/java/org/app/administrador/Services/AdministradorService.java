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

        System.out.println(idMonopatin.toString());

        //traer monopatin
        String url = "http://localhost:8082/monopatines/" + idMonopatin.getId();
        ResponseEntity<?> response = this.http.getRequest(token, url);

        //si el monopatin es valido
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            Monopatin monopatin = (Monopatin) response.getBody();

            System.out.println("mono: " + monopatin.toString());

            //si el monopatin no esta en mantenimiento
            if(!monopatin.isEnMantenimiento()) {
                MantenimientoDTO m = new MantenimientoDTO(idMonopatin.getIdMonopatin());
                String url2 = "/mantenimiento";

                ResponseEntity<?> resp = this.http.postRequest(token, url2, m);
                //si se creo correctamente
                if(resp.getStatusCode().is2xxSuccessful()) {
                    System.out.println("Vamos!!!!!");
                    String url3 = "/monopatines/" + idMonopatin.getId() + "/mantenimiento/" + true;
                    ResponseEntity<?> resp2 = this.http.putRequest(token,url3);

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

        ResponseEntity<?> response = this.http.getRequest(token, url);
//        ResponseEntity<ReporteTotalFacturadoDTO> response = rest.exchange(
//                url,
//                HttpMethod.GET,
//                requestEntity,
//                new ParameterizedTypeReference<ReporteTotalFacturadoDTO>(){}
//        );
//        headers.setContentType(MediaType.APPLICATION_JSON);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No se ha conseguido el reporte de la facturación con éxito.");
        }
    }

    // aca dio ERROR!!!!!
    // REVISAR IMPLEMENTACION CON HTTPSERVICE
    public ResponseEntity<?> definirPrecio(String token, Tarifa t){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        RestTemplate rUltimaTarifa = new RestTemplate();
        ResponseEntity<TarifaDTO> responseUltima = rUltimaTarifa.exchange(
                "http://localhost:8080/tarifas/ultima",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<TarifaDTO>() {}
        );

        String url = "/tarifas/ultima";

        //ResponseEntity<?> response = this.http.getRequest(token, url );
        //Si existe la tarifa
        if(responseUltima.getStatusCode().is2xxSuccessful() && responseUltima.getBody() != null){
            TarifaDTO ultimaTarifa = (TarifaDTO) responseUltima.getBody();

            System.out.println(ultimaTarifa.toString());
            //pregunto si las fechas son validas
            if(t.getFecha_creacion().after(ultimaTarifa.getFecha_caducacion())){

                String url2 = "/tarifas";
                System.out.println(url2);
//                ResponseEntity<?> response2 = this.http.postRequest(token, url, t);

                HttpEntity<Tarifa> requestTarifa = new HttpEntity<>(t, headers);
                RestTemplate r = new RestTemplate();

                ResponseEntity<Tarifa> response = r.exchange(
                        "http://localhost:8082/tarifas",
                        HttpMethod.POST,
                        requestTarifa,
                        new ParameterizedTypeReference<Tarifa>() {}
                );
                System.out.println(response);
                if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
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
