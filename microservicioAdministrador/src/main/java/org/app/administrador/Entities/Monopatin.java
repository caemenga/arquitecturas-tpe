package org.app.administrador.Entities;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monopatin implements Serializable {
    private Long id;
    private Long paradaEstacionamientoId;
    private Long latitud;
    private Long altitud;
    private boolean enMantenimiento;

    public Monopatin(Long id, boolean enMantenimiento) {
        this.id = id;
        this.enMantenimiento = enMantenimiento;
    }


    public Long getId() {
        return id;
    }

    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }

    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }
}
