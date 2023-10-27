package org.app.mantenimiento.services;

import org.app.mantenimiento.entities.Mantenimiento;
import org.app.mantenimiento.repositories.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mantenimiento")
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientoRepository.findAll();
    }

    public Mantenimiento addMantenimiento(Mantenimiento m) {
        return mantenimientoRepository.save(m);
    }

    public Optional<Mantenimiento> getById(Long id) {
        return mantenimientoRepository.findById(id);
    }

    public boolean deleteMantenimiento(long id) throws Exception {
        try{
            if(mantenimientoRepository.existsById(id)){
                mantenimientoRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
