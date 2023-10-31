package org.app.administrador.Entities.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class MonopatinDTO implements Serializable {
    private Long idMonopatin;

    public MonopatinDTO(Long id) {
        this.idMonopatin = id;
    }

    public MonopatinDTO() {
    }

    public Long getId() {
        return idMonopatin;
    }


//    public boolean isEnMantenimiento() {
//        return enMantenimiento;
//    }
//
//    public void setEnMantenimiento(boolean enMantenimiento) {
//        this.enMantenimiento = enMantenimiento;
//    }
}