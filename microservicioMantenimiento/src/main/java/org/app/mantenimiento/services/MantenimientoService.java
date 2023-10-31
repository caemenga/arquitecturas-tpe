package org.app.mantenimiento.Services;

import org.app.mantenimiento.entities.DTO.MantenimientoDTO;
import org.app.mantenimiento.entities.Mantenimiento;
import org.app.mantenimiento.Repositories.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service("mantenimiento")
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientoRepository.findAll();
    }

    public Mantenimiento addMantenimiento(Long idMonopatin) {
        Mantenimiento m = new Mantenimiento(idMonopatin);
        System.out.println(m);
        return mantenimientoRepository.save(m);
    }

    public Optional<Mantenimiento> getById(Long id) {
        return mantenimientoRepository.findById(id);
    }

    public boolean deleteMantenimiento(Long id) throws Exception {
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

    public Mantenimiento finMantenimiento(MantenimientoDTO idMantenimiento) {
        Optional<Mantenimiento> m = mantenimientoRepository.findById(idMantenimiento.getIdMantenimiento());

        if(m.isPresent()){
            long currentTimeMillis = System.currentTimeMillis();
            Date fecha = new Date(currentTimeMillis);
            m.get().setFinMantenimiento(fecha);
            return mantenimientoRepository.save(m.get());
        }
        return null;
    }
}
