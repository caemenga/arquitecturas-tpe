package org.app.mantenimiento.controllers;

import lombok.RequiredArgsConstructor;
import org.app.mantenimiento.services.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/mantenimiento")
@RequiredArgsConstructor
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;
    @Autowired
    private RestTemplate restTemplate;
}
