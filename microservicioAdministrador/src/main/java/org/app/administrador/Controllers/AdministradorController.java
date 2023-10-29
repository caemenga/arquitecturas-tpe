package org.app.administrador.Controllers;

import lombok.Data;
import org.app.administrador.Entities.Monopatin;
import org.app.administrador.Entities.Parada;
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
    @Autowired
    private RestTemplate restTemplate;

    //Registrar monopatin en mantenimiento
    @PostMapping("/mantenimiento/registrar")
    public ResponseEntity<?> registrarMantenimiento(@RequestBody long idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.registrarMantenimiento(idMonopatin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

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
    @GetMapping("/monopatin/{idMonopatin}/ubicar")
    public ResponseEntity<?> ubicarMonopatinEnParada(@PathVariable("idMonopatin") long idMonopatin){
        try{
            return null;
            //return ResponseEntity.status(HttpStatus.OK).body(administradorService.ubicarMonopatinEnParada(idMonopatin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    //Agregar monopatin
    @PostMapping("/monopatin")
    public ResponseEntity<?> addMonopatin(@RequestBody Monopatin monopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.addMonopatin(monopatin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
//
    //quitar monopatin
    @DeleteMapping(path = "/monopatin/{id}")
    public ResponseEntity<?> eliminarMonopatin(@PathVariable("id") long idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.eliminarMonopatin(idMonopatin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    //Registrar parada
    @PostMapping("/parada")
    public ResponseEntity<?> addParada(@RequestBody Parada parada){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.addParada(parada));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
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
//    //definir precio
//    @PostMapping(path = "/tarifa/{tarifa}")
//    public ResponseEntity<?> definirPrecio(@RequestBody long tarifa, long tarifaExtra){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.definirPrecio(tarifa));
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }
//
//    //definir tarifa extra para reinicio por pausa extensa
//
//    //anular cuenta
//    @PutMapping(path = "/cuenta/anular/{id}")
//    public ResponseEntity<?> anularCuenta(@PathVariable long idCuenta){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.anularCuenta(idCuenta));
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }
//
//    //generar reporte de uso de monopatines por KM
//    @GetMapping( path = "/monopatines/km")
//    public ResponseEntity<?> reporteMonopatinesPorKM(){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(administradorService.reporteMonopatinesPorKM());
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
//        }
//    }
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

}
