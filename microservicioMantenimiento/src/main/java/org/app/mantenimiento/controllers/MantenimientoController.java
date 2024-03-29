package org.app.mantenimiento.controllers;

import lombok.RequiredArgsConstructor;
import org.app.mantenimiento.entities.DTO.MantenimientoDTO;
import org.app.mantenimiento.entities.Mantenimiento;
import org.app.mantenimiento.services.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/mantenimiento")
@RequiredArgsConstructor
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public ResponseEntity<?> getMantenimientos(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.getMantenimientos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addMantenimiento(@RequestBody MantenimientoDTO idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.addMantenimiento(idMonopatin));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping()
    public ResponseEntity<?> finMantenimiento(@RequestBody MantenimientoDTO idMantenimiento){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.finMantenimiento(idMantenimiento));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mantenimientoService.deleteMantenimiento(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }

    //http://localhost:8085/mantenimiento/reporte/monopatines?pausa=true
    //http://localhost:8085/mantenimiento/reporte/monopatines?pausa=false
    @GetMapping( path = "/reporte/monopatines")
    public ResponseEntity<?> getReporteKms(@RequestHeader("Authorization") String token, @RequestParam Boolean pausa) {
        try {
            String _token = token.split(" ")[1];
            return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.getReporteKms(_token, pausa));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
