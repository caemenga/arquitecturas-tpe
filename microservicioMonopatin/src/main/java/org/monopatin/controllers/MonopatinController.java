package org.monopatin.controllers;

import lombok.RequiredArgsConstructor;
import org.monopatin.entities.Monopatin;
import org.monopatin.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/monopatines")
@RequiredArgsConstructor
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public ResponseEntity<?> getMonopatines(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.getMonopatines());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addMonopatin(@RequestBody Monopatin m){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.addMonopatin(m));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(monopatinService.deleteMonopatin(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }
}
