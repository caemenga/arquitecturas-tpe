package org.app.monopatin.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha_creacion;
    private Double tarifa;
    private Double porc_recargo;
    private Date fecha_caducacion;

    public Tarifa() {
    }

    public Tarifa(Double tarifa, Double porc_recargo) {
        this.fecha_creacion = setFechaCreacion();
        this.tarifa = tarifa;
        this.porc_recargo = porc_recargo;
        this.fecha_caducacion = null;
    }
    public Tarifa(Double tarifa, Double porc_recargo, Date fecha_creacion, Date fecha_caducacion) {
        this.fecha_creacion = fecha_creacion;
        this.tarifa = tarifa;
        this.porc_recargo = porc_recargo;
        this.fecha_caducacion = fecha_caducacion;
    }

    public Date setFechaCreacion(){
        long currentTimeMillis = System.currentTimeMillis();

        // Crea una instancia de java.sql.Date a partir de los milisegundos actuales
        return new Date(currentTimeMillis);
    }
}
