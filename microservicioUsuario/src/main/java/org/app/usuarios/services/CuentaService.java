package org.app.usuarios.services;

import org.app.usuarios.repositories.CuentaRepository;
import org.app.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cuentas")
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
}
