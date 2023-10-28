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
    private Integer paradaEstacionamientoId;
    @Column
    private long latitud;
    @Column
    private long altitud;
    @Column
    private boolean enMantenimiento;

    public Monopatin(Integer paradaEstacionamientoId, long latitud, long altitud) {
        this.paradaEstacionamientoId = paradaEstacionamientoId;
        this.latitud = latitud;
        this.altitud = altitud;
        this.enMantenimiento = false;
    }

    public Monopatin() {
    }
}
