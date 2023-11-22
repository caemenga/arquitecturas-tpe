package org.app.usuarios.controllers;


import lombok.RequiredArgsConstructor;
import org.app.usuarios.entities.DTO.UbicacionDTO;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.services.AuthorityConstant;
import org.app.usuarios.services.CuentaService;
import org.app.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getUsuarios(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUsuarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }


    //localhost:8081/usuarios/monopatinesCercanos/latitud/47.99/longitud/237.4
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    @GetMapping( path = "/monopatinesCercanos/latitud/{latitud}/longitud/{longitud}")
    public ResponseEntity<?> getMonopatinesCercanos(@RequestHeader("Authorization") String token, @PathVariable("latitud") double latitud, @PathVariable("longitud") double longitud){
        try{
            String _token = token.split(" ")[1];
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getMonopatinesCercanos(_token, latitud,longitud));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }


    @DeleteMapping(path = "/{id}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioService.deleteUsuario(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }
    @PutMapping(path = "/cuenta/{id}/agregar/{saldo}")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.USER + "\")")
    public ResponseEntity<?> cargarSaldo(@PathVariable("id") Long id, @PathVariable("saldo") Double saldo){

        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(cuentaService.agregarSaldo(id, saldo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo cargar saldo en la cuenta  \"" + id + ". intente nuevamente.\"}");
        }
    }


}
