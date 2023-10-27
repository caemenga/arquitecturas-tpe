package org.app.viajes.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.app.viajes.models.DTO.Monopatin;

import java.util.Date;

@Entity
@Data
@Getter
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@Column
    //private Cuenta cuenta;
    //@Column
    //private Monopatin monopatin;
    @Column
    private Date fechaHoraInicio;
    @Column
    private Date fechaHoraFin;
    @Column
    private double kilometros;

    //falta constructor con params.

    public Viaje() {
    }
}
