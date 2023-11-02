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

    private Date fechaCreacion;
    @Column
    private Double valor;
    @Column
    private Double porcRecargo;
    @Column
    private Date fechaCaducacion;

    public Tarifa() {
    }

    public Tarifa(Double tarifa, Double porc_recargo) {
        this.fechaCreacion = setFechaCreacion();
        this.valor = tarifa;
        this.porcRecargo = porc_recargo;
        this.fechaCaducacion = null;
    }
    public Tarifa(Date fecha_creacion,Double tarifa, Double porc_recargo,  Date fecha_caducacion) {
        this.fechaCreacion = fecha_creacion;
        this.valor = tarifa;
        this.porcRecargo = porc_recargo;
        this.fechaCaducacion = fecha_caducacion;
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
