package org.app.monopatin.controllers;

import lombok.RequiredArgsConstructor;
import org.app.monopatin.entities.Tarifa;
import org.app.monopatin.services.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tarifas")
@RequiredArgsConstructor
public class TarifaController {
    @Autowired
    private TarifaService tarifaService;


    @PostMapping()
    public ResponseEntity<?> postTarifa(@RequestBody Tarifa tarifa){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.addTarifa(tarifa));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping("/ultima")
    public ResponseEntity<?> getUltimaTarifa(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.getUltimaTarifa());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.No se pudo recuperar la ultima tarifa.\"}");
        }
    }


}
