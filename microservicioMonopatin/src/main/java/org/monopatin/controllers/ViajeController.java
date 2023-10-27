package org.monopatin.controllers;

import lombok.RequiredArgsConstructor;
import org.monopatin.entities.Viaje;
import org.monopatin.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getViajes(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getViajes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addViaje(@RequestBody Viaje v){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.addViaje(v));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(viajeService.deleteViaje(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }
}
