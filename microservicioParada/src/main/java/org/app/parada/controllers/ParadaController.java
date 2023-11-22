package org.app.parada.controllers;

import lombok.RequiredArgsConstructor;
import org.app.parada.entities.Parada;
import org.app.parada.security.AuthorityConstant;
import org.app.parada.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/paradas")
@RequiredArgsConstructor
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @GetMapping()
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getParadas(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.getParadas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    //http://localhost:8083/paradas/cercana?latitud=30.1&longitud=270.5
    //MATCH CON PARADA ID = 2
    @GetMapping("/cercana")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getParadaCercana(@RequestParam double latitud, @RequestParam double longitud){
        System.out.println("a: " + latitud + " l: " + longitud);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.getParadaCercana(latitud, longitud));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping()
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> addParada(@RequestBody Parada p){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.addParada(p));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> deleteByID(@PathVariable("id") String id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(paradaService.deleteParada(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }
}
