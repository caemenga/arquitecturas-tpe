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

//    public Mantenimiento(Long monopatinId, Date inicioMantenimiento, Date finMantenimiento) {
//        this.monopatinId = monopatinId;
//        this.inicioMantenimiento = inicioMantenimiento;
//        this.finMantenimiento = finMantenimiento;
//    }

    public Mantenimiento(Long monopatinId, Date inicioMantenimiento) {
        this.monopatinId = monopatinId;
        this.inicioMantenimiento = inicioMantenimiento;
        this.finMantenimiento = null;
    }

    public Mantenimiento(Long monopatinId) {
        this.monopatinId = monopatinId;
        this.inicioMantenimiento = this.obtenerFechaActual();
        this.finMantenimiento = null;
    }

    public Mantenimiento() {
    }

    public Date obtenerFechaActual(){
        // Obtén la fecha y hora actual como un valor en milisegundos
        long currentTimeMillis = System.currentTimeMillis();

        // Crea una instancia de java.sql.Date a partir de los milisegundos actuales
        return new Date(currentTimeMillis);
    }

    public void setFinMantenimiento(){
        long currentTimeMillis = System.currentTimeMillis();

        // Crea una instancia de java.sql.Date a partir de los milisegundos actuales
        this.finMantenimiento = new Date(currentTimeMillis);
    }

    @Override
    public String toString() {
        return "Mantenimiento{" +
                "id=" + id +
                ", monopatinId=" + monopatinId +
                ", inicioMantenimiento=" + inicioMantenimiento +
                ", finMantenimiento=" + finMantenimiento +
                '}';
    }
}
