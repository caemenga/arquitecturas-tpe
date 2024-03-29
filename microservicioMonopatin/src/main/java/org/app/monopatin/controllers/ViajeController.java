package org.app.monopatin.controllers;

import lombok.RequiredArgsConstructor;
import org.app.monopatin.entities.Viaje;
import org.app.monopatin.security.AuthorityConstant;
import org.app.monopatin.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/viajes")
@RequiredArgsConstructor
public class ViajeController {

    @Autowired
    private ViajeService viajeService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getViajes(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getViajes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping()
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> addViaje(@RequestBody Viaje v){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.addViaje(v));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(viajeService.deleteViaje(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }

    //http://localhost:8082/viajes/reporte/kms?pausa=false
    //http://localhost:8082/viajes/reporte/kms?pausa=true
    @GetMapping( path = "/reporte/kms")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getReporteKms(@RequestParam Boolean pausa) {
        try {
            if (pausa) {
                return ResponseEntity.status(HttpStatus.OK).body(viajeService.getReporteKmsPausa());
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(viajeService.getReporteKms());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    //http://localhost:8082/viajes/reporte/valores
    @GetMapping( path = "/reporte/valores")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getReporteTotalFacturado(@RequestParam Long mes1, @RequestParam Long mes2, @RequestParam Long anio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getReporteTotalFacturado(mes1,mes2,anio));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

}
