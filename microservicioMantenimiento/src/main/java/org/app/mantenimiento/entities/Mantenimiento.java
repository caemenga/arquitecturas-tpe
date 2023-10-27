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
    private Long id;
    @Column
    private Long monopatinId;
    @Column
    private Date inicioMantenimiento;
    @Column
    private Date finMantenimiento;

    public Mantenimiento(Long monopatinId, Date inicioMantenimiento, Date finMantenimiento) {
        this.monopatinId = monopatinId;
        this.inicioMantenimiento = inicioMantenimiento;
        this.finMantenimiento = finMantenimiento;
    }

    public Mantenimiento() {
    }
}
