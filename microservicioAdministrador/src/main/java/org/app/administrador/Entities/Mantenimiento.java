package org.app.administrador.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;


@Data
@Getter
@Setter

public class Mantenimiento extends Entity implements Serializable {
    private Long id;
    private Long monopatinId;
    private Date inicioMantenimiento;
    private Date finMantenimiento;

    public Mantenimiento(Long id, Long monopatinId, Date inicioMantenimiento, Date finMantenimiento) {
        this.id = id;
        this.monopatinId = monopatinId;
        this.inicioMantenimiento = inicioMantenimiento;
        this.finMantenimiento = finMantenimiento;
    }

    public Mantenimiento() {
    }
}