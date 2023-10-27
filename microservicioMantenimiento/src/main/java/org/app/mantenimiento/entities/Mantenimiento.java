package org.app.mantenimiento.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Getter
@Setter
public class Mantenimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@Column
    //private Monopatin monopatin;
    @Column
    private Date inicioMantenimiento;
    @Column
    private Date finMantenimiento;

    /*public Mantenimiento(int id, Monopation monopatin, Date inicioMantenimiento, Date finMantenimiento) {
        this.id = id;
        this.monopatin = monopatin;
        this.inicioMantenimiento = inicioMantenimiento;
        this.finMantenimiento = finMantenimiento;
    }*/

    public Mantenimiento() {
    }
}
