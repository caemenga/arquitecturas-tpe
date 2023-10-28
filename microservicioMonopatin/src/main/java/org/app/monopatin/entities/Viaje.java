package org.app.monopatin.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@Getter
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int cuentaId;
    @ManyToOne
    @JoinColumn(name="idMonopatin")
    private Monopatin monopatin;
    @Column
    private Date fechaHoraInicio;
    @Column
    private Date fechaHoraFin;
    @Column
    private double kilometros;
    @Column
    private Time pausa;

    public Viaje(int cuentaId, Monopatin monopatin, Date fechaHoraInicio, Date fechaHoraFin, double kilometros, Time pausa) {
        this.cuentaId = cuentaId;
        this.monopatin = monopatin;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.kilometros = kilometros;
        this.pausa = pausa;
    }

    public Viaje() {
    }
}
