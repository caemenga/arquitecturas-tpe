package org.app.mantenimiento.services;

import org.app.mantenimiento.repositories.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mantenimiento")
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;
}
