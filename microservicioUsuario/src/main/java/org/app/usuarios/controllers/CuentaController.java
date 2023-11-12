package org.app.usuarios.controllers;

import lombok.RequiredArgsConstructor;
import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.entities.DTO.CuentaDTO;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.services.CuentaService;
import org.app.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public ResponseEntity<?> getCuentas(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.getCuentas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addCuenta(@RequestBody Cuenta c){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.addCuenta(c));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping( path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se encuentra el objeto buscado");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cuentaService.deleteCuenta(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar el alumno con id  \"" + id + ". intente nuevamente.\"}");
        }
    }

    //todo: método para inhabilitar cuenta.

    @PutMapping(path = "/anular/{id}")
    public ResponseEntity<?> inhabilitarCuenta(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.inhabilitarCuenta(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No existe la cuenta con la id: " + id +"\"}");
        }
    }

    //todo: método para agregar saldo.

    @PutMapping(path = "/{id}/agregar/{saldo}")
    public ResponseEntity<?> agregarSaldo(@PathVariable("id") Long id , @PathVariable("saldo") Double saldo){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.agregarSaldo(id, saldo));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No existe la cuenta con la id: " + saldo +"\"}");
        }
    }

}
