package org.app.administrador.Controllers;

import lombok.Data;
import org.app.administrador.Entities.DTO.*;
import org.app.administrador.Entities.DTO.MonopatinDTO;
import org.app.administrador.Entities.DTO.MonopatinParadaDTO;
import org.app.administrador.Entities.Monopatin;
import org.app.administrador.Entities.Parada;
import org.app.administrador.Entities.Tarifa;
import org.app.administrador.Services.AdministradorService;
import org.app.administrador.security.AuthorityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Data
@RequestMapping("/administracion")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    //Registrar monopatin en mantenimiento
    @PostMapping("/mantenimiento/registrar")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> registrarMantenimiento(@RequestHeader("Authorization") String token, @RequestBody MonopatinDTO idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.registrarMantenimiento(this.getToken(token), idMonopatin));
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    //Agregar monopatin
    @PostMapping("/monopatines")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> addMonopatin(@RequestHeader("Authorization") String token, @RequestBody Monopatin monopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.addMonopatin(this.getToken(token), monopatin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    //definir precio
//    http://localhost:8080/administracion/tarifa
//    {
//        "tarifa": 10,
//        "porc_recargo": 0.3,
//        "fecha_creacion": "2024-02-12T10:00:00Z",
//        "fecha_caducacion": "2024-03-12T10:00:00Z"
//    }
    @PostMapping(path = "/tarifa")
    // aca dio ERROR!!!!!
    // REVISAR IMPLEMENTACION CON HTTPSERVICE
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> definirPrecio(@RequestHeader("Authorization") String token, @RequestBody Tarifa t){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.definirPrecio(this.getToken(token), t));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // anular cuenta
    //http://localhost:8085/administracion/cuenta/anular/2
    @PutMapping( path = "/cuenta/anular/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> anularCuenta(@RequestHeader("Authorization") String token, @PathVariable("id") Long idCuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.anularCuenta(this.getToken(token), idCuenta));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
//
    //generar reporte de uso de monopatines por KM
    //http://localhost:8080/administracion/monopatines/viajes?cant=1&anio=2023
    @GetMapping("/monopatines/viajes")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getMonopatinesPorXViajes(@RequestHeader("Authorization") String token, @RequestParam Long cant, @RequestParam Long anio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.getMonopatinesPorXViajes(this.getToken(token), cant, anio));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    //http://localhost:8080/administracion/viajes?mes1=1&mes2=12&anio=2023
    @GetMapping( path = "/viajes")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getReporteTotalFacturado(@RequestHeader("Authorization") String token, @RequestParam Long mes1,@RequestParam Long mes2,@RequestParam Long anio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.getReporteTotalFacturado(this.getToken(token), mes1,mes2,anio));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    //http://localhost:8082/administracion/monopatines/reporte/en-operacion
    @GetMapping( path = "/monopatines/reporte/en-operacion")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getReporteEnOperacion(@RequestHeader("Authorization") String token){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.getReporteEnOperacion(this.getToken(token)));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    public String getToken(String token){
        return token.split(" ")[1];
    }
}
