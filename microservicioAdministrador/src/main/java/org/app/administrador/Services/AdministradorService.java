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
        String url = "/monopatines/" + idMonopatin.getId();
        ParameterizedTypeReference<Monopatin> o = new ParameterizedTypeReference<Monopatin>() {};
        ResponseEntity<Monopatin> response = this.http.getRequest(token, url, o);

        //si el monopatin es valido
        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            Monopatin monopatin = response.getBody();

            System.out.println("mono: " + monopatin.toString());

            //si el monopatin no esta en mantenimiento
            if(!monopatin.isEnMantenimiento()) {

                MantenimientoDTO m = new MantenimientoDTO(idMonopatin.getIdMonopatin());
                String url2 = "/mantenimiento";
                ParameterizedTypeReference<Mantenimiento> Mant = new ParameterizedTypeReference<Mantenimiento>() {};

                ResponseEntity<Mantenimiento> resp = this.http.postRequest(token, url2, m, Mant);

                //si se creo correctamente
                if(resp.getStatusCode().is2xxSuccessful()) {
                    System.out.println("Vamos!!!!!");

                    String url3 = "/monopatines/" + idMonopatin.getId() + "/mantenimiento/" + true;
                    ParameterizedTypeReference<Monopatin> mono = new ParameterizedTypeReference<Monopatin>() {};
                    ResponseEntity<Monopatin> resp2 = this.http.putRequest(token,url3, mono);

                    return ResponseEntity.ok("Monopatin agregado a mantenimiento con exito");
                }
            }
        }
        return ResponseEntity.ok("agregado c/ exito");
    }

    public ResponseEntity<?> addMonopatin(String token, Monopatin monopatin) {

        String url = "/monopatin";
        ParameterizedTypeReference<Monopatin> monopatinParameterizedTypeReference = new ParameterizedTypeReference<Monopatin>() {};
        ResponseEntity<Monopatin> response = this.http.postRequest(token, url, monopatin, monopatinParameterizedTypeReference);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No se ha podido crear el monopatin con exito");
        }
    }

   public ResponseEntity<?> anularCuenta(String token, Long idCuenta) {
       //Traer Cuenta
       String url = "/cuentas/" + idCuenta;
       ParameterizedTypeReference<Cuenta> cuenta = new ParameterizedTypeReference<Cuenta>() {};
       ResponseEntity<Cuenta> response = this.http.getRequest(token, url, cuenta);

       //si la cuenta existe
       if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
           String url2 = "/cuentas/anular/" + idCuenta;
           this.http.putRequest(token,url2, cuenta);
           return ResponseEntity.ok("Cuenta id: " + idCuenta + " anulada exitosamente");
       }
       return ResponseEntity.ok("La cuenta no existe");
    }


    public ResponseEntity<?> getReporteTotalFacturado(String token, Long mes1, Long mes2, Long anio) {

        //?mes1=1&mes2=12&anio=2023
        String url = "/viajes/reporte/valores?mes1="+mes1+"&mes2="+mes2+"&anio="+anio;
        ParameterizedTypeReference<ReporteTotalFacturadoDTO> o = new ParameterizedTypeReference<ReporteTotalFacturadoDTO>() {};

        ResponseEntity<ReporteTotalFacturadoDTO> response = this.http.getRequest(token, url, o);


        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No se ha conseguido el reporte de la facturación con éxito.");
        }
    }

    public ResponseEntity<?> definirPrecio(String token, Tarifa t){


        ParameterizedTypeReference<TarifaDTO> tarifaDTOParameterizedTypeReference = new ParameterizedTypeReference<>() {};
        String url = "/tarifas/ultima";
        ResponseEntity<TarifaDTO> responseUltima = this.http.getRequest(token, url, tarifaDTOParameterizedTypeReference);


        //Si existe la tarifa
        if(responseUltima.getStatusCode().is2xxSuccessful() && responseUltima.getBody() != null){

            TarifaDTO ultimaTarifa = responseUltima.getBody();

            //pregunto si las fechas son validas
            if(t.getFecha_creacion().after(ultimaTarifa.getFecha_caducacion())){

                String url2 = "/tarifas";
                ParameterizedTypeReference<Tarifa> tarifaParameterizedTypeReference = new ParameterizedTypeReference<>() {};
                ResponseEntity<Tarifa> response = this.http.postRequest(token, url2, t, tarifaParameterizedTypeReference);


                if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
                    return ResponseEntity.ok(response.getBody());
                } else {
                    return ResponseEntity.ok("No se ha podido crear la tarifa con exito, NO ANDUVO EL POST REQUEST.");
                }
            }
            return ResponseEntity.ok("Ya existe una tarifa en la misma fecha");
        }
         return ResponseEntity.ok("No se ha podido crear la tarifa con exito. revisar problema. (ULTIMA tarifa)");
    }

    public ResponseEntity<?> getMonopatinesPorXViajes(String token, Long cant, Long anio) {

        String url = "/monopatines/viajes?cant=" + cant + "&anio=" + anio;
        ParameterizedTypeReference<List<MonopatinViajeDTO>> monopatinViajeDTOParameterizedTypeReference = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<MonopatinViajeDTO>> response = this.http.getRequest(token, url, monopatinViajeDTOParameterizedTypeReference);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No hay reporte de monopatines.");
        }
    }

    public ResponseEntity<?> getReporteEnOperacion(String token) {

        String url = "/monopatines/reporte/operacion";
        ParameterizedTypeReference<ReporteOperacion> reporteOperacionParameterizedTypeReference = new ParameterizedTypeReference<>() {};

        ResponseEntity<ReporteOperacion> response = this.http.getRequest(token, url, reporteOperacionParameterizedTypeReference);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.ok("No hay reporte de monopatines.");
        }
    }

}
