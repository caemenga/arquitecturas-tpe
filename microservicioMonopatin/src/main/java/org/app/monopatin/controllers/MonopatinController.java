package org.app.monopatin.controllers;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.app.monopatin.entities.Monopatin;
import org.app.monopatin.security.AuthorityConstant;
import org.app.monopatin.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getMonopatines(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.getMonopatines());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    //http://localhost:8082/monopatines/viajes?cant=1&anio=2023
    @GetMapping("/viajes")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getMonopatinesPorXViajes(@RequestParam Long cant, @RequestParam Long anio){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.getMonopatinesPorXViajes(cant,anio));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping()
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> addMonopatin(@RequestBody Monopatin m){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.addMonopatin(m));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }

    //monopatines/2/mantenimiento/true
    @PutMapping( path = "/{id}/mantenimiento/{boolean}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> setearMantenimiento(@PathVariable("id") Long id, @PathVariable("boolean") boolean bol){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.setearMantenimiento(id, bol));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }
    //http://localhost:8082/monopatines/parada/" + p.getId(),
    @GetMapping("/parada/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.USER + "\")" )
    public ResponseEntity<?> getMonopatinesPorParada(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.getMonopatinesPorParada(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //http://localhost:8082/monopatines/reporte/operacion
    @GetMapping("/reporte/operacion")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> reporteEnOperacion(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinService.reporteEnOperacion());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @GetMapping("/reporte/mantenimiento")

    @DeleteMapping(path = "/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(monopatinService.deleteMonopatin(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }
}
