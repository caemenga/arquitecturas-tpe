package org.app.viajes.services;

import org.app.viajes.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("viajes")
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;
}
