package org.app.administrador.Entities;

import java.io.Serializable;


public class Monopatin implements Serializable {
    private Long id;
    private long paradaEstacionamientoId;
    private long latitud;
    private long altitud;
    private boolean enMantenimiento;

    public Monopatin(Long id, boolean enMantenimiento) {
        this.id = id;
        this.enMantenimiento = enMantenimiento;
    }

    public Monopatin(Long id, long paradaEstacionamientoId, long latitud, long altitud, boolean enMantenimiento) {
        this.id = id;
        this.paradaEstacionamientoId = paradaEstacionamientoId;
        this.latitud = latitud;
        this.altitud = altitud;
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
