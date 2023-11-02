package org.app.administrador.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Tarifa {
    private Date fecha_creacion;
    private Double tarifa;
    private Double porc_recargo;
    private Date fecha_caducacion;

    public Tarifa() {
    }

    public Tarifa(Double tarifa, Double porc_recargo) {
        this.tarifa = tarifa;
        this.porc_recargo = porc_recargo;
        this.fecha_creacion = setFechaCreacion();
        this.fecha_caducacion = null;
    }
    public Tarifa(Double tarifa, Double porc_recargo, Date fecha_creacion, Date fecha_caducacion) {
        this.tarifa = tarifa;
        this.porc_recargo = porc_recargo;
        this.fecha_creacion = fecha_creacion;
        this.fecha_caducacion = fecha_caducacion;
    }

    public Date setFechaCreacion(){
        long currentTimeMillis = System.currentTimeMillis();

        // Crea una instancia de java.sql.Date a partir de los milisegundos actuales
        return new Date(currentTimeMillis);
    }
}
