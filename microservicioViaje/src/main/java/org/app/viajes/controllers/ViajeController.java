package org.app.viajes.controllers;

import lombok.RequiredArgsConstructor;
import org.app.viajes.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/viajes")
@RequiredArgsConstructor
public class ViajeController {

    @Autowired
    private ViajeService viajeService;
    @Autowired
    private RestTemplate restTemplate;
}
