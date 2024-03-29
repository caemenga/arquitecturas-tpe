package org.app.monopatin.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long paradaEstacionamientoId;
    @Column
    private double latitud;
    @Column
    private double longitud;
    @Column
    private boolean enMantenimiento;

    public Monopatin(Long paradaEstacionamientoId, double latitud, double longitud) {
        this.paradaEstacionamientoId = paradaEstacionamientoId;
        this.latitud = latitud;
        this.longitud = longitud;
        this.enMantenimiento = false;
    }

    public Monopatin() {
    }
}
