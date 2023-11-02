package org.app.administrador.Controllers;

import lombok.Data;
import org.app.administrador.Entities.DTO.*;
import org.app.administrador.Entities.DTO.MonopatinDTO;
import org.app.administrador.Entities.DTO.MonopatinParadaDTO;
import org.app.administrador.Entities.Monopatin;
import org.app.administrador.Entities.Parada;
import org.app.administrador.Entities.Tarifa;
import org.app.administrador.Services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> registrarMantenimiento(@RequestBody MonopatinDTO idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.registrarMantenimiento(idMonopatin));
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

//    @GetMapping( path = "/monopatines/km")
//    public ResponseEntity<?> reporteMonopatinesPorKM(){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.reporteMonopatinesPorKM());
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }

    //Registrar fin de mantenimiento
//    @PostMapping("/mantenimiento/fin")
//    public ResponseEntity<?> finMantenimiento(@RequestBody long idMantenimiento){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.finMantenimiento(idMantenimiento));
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }
//
    //ubicar monopatin en parada
    //@GetMapping("/monopatin/{idMonopatin}/ubicar")
    //public ResponseEntity<?> ubicarMonopatinEnParada(@PathVariable("idMonopatin") long idMonopatin){
    //    try{
    //        return null;
    //        //return ResponseEntity.status(HttpStatus.OK).body(administradorService.ubicarMonopatinEnParada(idMonopatin));
    //    } catch (Exception e){
    //        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
    //    }
    //}

//    @PostMapping("/monopatin/ubicar")
//    public ResponseEntity<?> ubicarMonopatinEnParada(@RequestBody MonopatinParadaDTO monopatinParadaId){
//        try{
//            //return null;
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.ubicarMonopatinEnParada(monopatinParadaId));
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }

    //Agregar monopatin
    @PostMapping("/monopatines")
    public ResponseEntity<?> addMonopatin(@RequestBody Monopatin monopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.addMonopatin(monopatin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
//
    //quitar monopatin
//    @DeleteMapping(path = "/monopatin/{id}")
//    public ResponseEntity<?> eliminarMonopatin(@PathVariable("id") long idMonopatin){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.eliminarMonopatin(idMonopatin));
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }

    //Registrar parada
//    @PostMapping("/parada")
//    public ResponseEntity<?> addParada(@RequestBody Parada parada){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.addParada(parada));
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }
//
//    //quitar parada
//    @DeleteMapping(path = "/parada/{id}")
//    public ResponseEntity<?> addParada(@RequestBody long idParada){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.deleteParada(idParada));
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }
//


    //definir precio
//    http://localhost:8080/administracion/tarifa
//    {
//        "tarifa": 10,
//        "porc_recargo": 0.3,
//        "fecha_creacion": "2024-02-12T10:00:00Z",
//        "fecha_caducacion": "2024-03-12T10:00:00Z"
//    }
    @PostMapping(path = "/tarifa")
    public ResponseEntity<?> definirPrecio(@RequestBody Tarifa t){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.definirPrecio(t));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
//
//    //definir tarifa extra para reinicio por pausa extensa
//
    // anular cuenta
    //http://localhost:8085/administracion/cuenta/anular/2
    @PutMapping( path = "/cuenta/anular/{id}")
    public ResponseEntity<?> anularCuenta(@PathVariable("id") Long idCuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.anularCuenta(idCuenta));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
//
    //generar reporte de uso de monopatines por KM
    //http://localhost:8080/administracion/monopatines/viajes?cant=1&anio=2023
@GetMapping("/monopatines/viajes")
    public ResponseEntity<?> getMonopatinesPorXViajes(@RequestParam Long cant, @RequestParam Long anio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.getMonopatinesPorXViajes(cant, anio));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
//
//    //Generar reporte de uso de monopatines por tiempo con pausas
//    @GetMapping( path = "/monopatines/conPausas")
//    public ResponseEntity<?> reporteMonopatinesPorPausas(){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.reporteMonopatinesPorPausas());
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }
//
//    //Generar reporte de uso de monopatines por tiempo sin pausas
//    @GetMapping( path = "/monopatines/sinPausas")
//    public ResponseEntity<?> reporteMonopatinesSinPausas(){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.reporteMonopatinesSinPausas());
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }

    //http://localhost:8080/administracion/viajes?mes1=1&mes2=12&anio=2023
    @GetMapping( path = "/viajes")
    public ResponseEntity<?> getReporteTotalFacturado(@RequestParam Long mes1,@RequestParam Long mes2,@RequestParam Long anio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.getReporteTotalFacturado(mes1,mes2,anio));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    //http://localhost:8082/administracion/monopatines/reporte/en-operacion
    @GetMapping( path = "/monopatines/reporte/en-operacion")
    public ResponseEntity<?> getReporteEnOperacion(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.getReporteEnOperacion());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }
}
