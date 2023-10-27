package org.app.usuarios.services;

import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.repositories.CuentaRepository;
import org.app.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("cuentas")
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> getCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta addCuenta(Cuenta c) {
        return cuentaRepository.save(c);
    }

    public Optional<Cuenta> getById(Long id) {
        return cuentaRepository.findById(id);
    }

    public boolean deleteCuenta(long id) throws Exception {
        try{
            if(cuentaRepository.existsById(id)){
                cuentaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
