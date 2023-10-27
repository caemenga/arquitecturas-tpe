package org.app.paradas.controllers;

import lombok.RequiredArgsConstructor;
import org.app.paradas.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/paradas")
@RequiredArgsConstructor
public class ParadaController {

    @Autowired
    private ParadaService paradaService;
    @Autowired
    private RestTemplate restTemplate;
}
