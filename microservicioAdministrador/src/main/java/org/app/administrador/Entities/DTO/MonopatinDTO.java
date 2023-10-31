package org.app.administrador.Entities.DTO;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDTO implements Serializable {
    private Long idMonopatin;

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