package org.app.monopatin.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Getter
//@AllArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long cuentaId;
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
    private Long pausa;
    @ManyToOne
    @JoinColumn(name="idTarifa")
    private Tarifa idTarifa;
    @Column
    private Double valorViaje;

    public Viaje(Long cuentaId, Monopatin monopatin, Date fechaHoraInicio, Date fechaHoraFin, double kilometros, Long pausa, Tarifa idTarifa) {
        this.cuentaId = cuentaId;
        this.monopatin = monopatin;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.kilometros = kilometros;
        this.pausa = pausa;
        this.idTarifa = idTarifa;
        this.valorViaje = setValorViaje();
    }

    public Viaje(Long id, Long cuentaId, Monopatin monopatin, Date fechaHoraInicio, double kilometros, Long pausa, Tarifa idTarifa) {
        this.id = id;
        this.cuentaId = cuentaId;
        this.monopatin = monopatin;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = null;
        this.kilometros = kilometros;
        this.pausa = pausa;
        this.idTarifa = idTarifa;
        this.valorViaje = null;
    }

    public Viaje() {
    }

    public double setValorViaje(){
        if(this.pausa>15){
            return (idTarifa.getTarifa()*((double) getFechaHoraFin().getTime() /(60 * 1000) - (double) getFechaHoraInicio().getTime() /(60 * 1000)) * idTarifa.getPorc_recargo());
        } else {

            return idTarifa.getTarifa()*((double) getFechaHoraFin().getTime() /(60 * 1000) - (double) getFechaHoraInicio().getTime() /(60 * 1000));
        }
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
        this.setValorViaje();
    }
}
