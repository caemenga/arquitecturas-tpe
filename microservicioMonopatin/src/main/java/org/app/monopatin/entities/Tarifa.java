package org.app.monopatin.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date fecha_creacion;
    @Column
    private Double tarifa;
    @Column
    private Double porc_recargo;
    @Column
    private Date fecha_caducacion;

    public Tarifa() {
    }

    public Tarifa(Double tarifa, Double porc_recargo) {
        this.fecha_creacion = setFechaCreacion();
        this.tarifa = tarifa;
        this.porc_recargo = porc_recargo;
        this.fecha_caducacion = null;
    }
    public Tarifa(Date fecha_creacion,Double tarifa, Double porc_recargo,  Date fecha_caducacion) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
