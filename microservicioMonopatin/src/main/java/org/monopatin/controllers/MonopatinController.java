package org.monopatin.controllers;

import lombok.RequiredArgsConstructor;
import org.monopatin.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/monopatines")
@RequiredArgsConstructor
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;
    @Autowired
    private RestTemplate restTemplate;
}
