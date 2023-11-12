package org.app.usuarios.controllers;


import lombok.RequiredArgsConstructor;
import org.app.usuarios.entities.DTO.UbicacionDTO;
import org.app.usuarios.entities.DTO.UsuarioDTO;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.services.CuentaService;
import org.app.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.app.usuarios.utils.security.JWTToken;

import java.util.Optional;

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
    public ResponseEntity<?> getUsuarios(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUsuarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addUsuario(@RequestBody Usuario u){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.addUsuario(u));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }


    //localhost:8081/usuarios/monopatinesCercanos/latitud/47.99/longitud/237.4
    @GetMapping( path = "/monopatinesCercanos/latitud/{latitud}/longitud/{longitud}")
    public ResponseEntity<?> getMonopatinesCercanos(@PathVariable("latitud") double latitud, @PathVariable("longitud") double longitud){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getMonopatinesCercanos(latitud,longitud));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioService.deleteUsuario(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }

    @PutMapping(path = "/cuenta/{id}/agregar/{saldo}")
    public ResponseEntity<?> cargarSaldo(@PathVariable("id") Long id, @PathVariable("saldo") Double saldo){

        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cuentaService.agregarSaldo(id, saldo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo cargar saldo en la cuenta  \"" + id + ". intente nuevamente.\"}");
        }
    }

    //el ejemplo (demoJWT.zip) pone que el login es con POST pero no entiendo dónde se supone que tenemos
    //que guardar la info del login.
    @PostMapping("/login")
    public UsuarioDTO login (@RequestParam String username, @RequestParam Long id) {
        Optional<Usuario> user = usuarioService.getById(id);

        if (user.isPresent()) {
            String token = JWTToken.getJWTToken(user.get().getRol());
            return new UsuarioDTO(username, user.get().getRol(), token);
        } else {
            return null;
        }
    }
}
