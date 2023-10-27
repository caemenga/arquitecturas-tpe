package org.monopatin.entities;

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
    private int id;
    /*@Column
    Parada paradaEstacionamiento;
    @Column
    private GPS gps;*/
    @Column
    private boolean enMantenimiento;

    /*public Monopatin(int id, Parada paradaEstacionamiento, GPS gps boolean enMantenimiento) {
        this.id = id;
        this.paradaEstacionamiento = paradaEstacionamiento;
        this.gps = gps;
        this.enMantenimiento = enMantenimiento;
    }*/

    public Monopatin() {
    }
}
