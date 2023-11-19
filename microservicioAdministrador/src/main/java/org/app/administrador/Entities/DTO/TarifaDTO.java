package org.app.administrador.Entities.DTO;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarifaDTO implements Serializable {
    private Long id;
    private Date fecha_creacion;
    private Double tarifa;
    private Double porc_recargo;
    private Date fecha_caducacion;


//    public Date setFechaCreacion() {
//        long currentTimeMillis = System.currentTimeMillis();
//
//        // Crea una instancia de java.sql.Date a partir de los milisegundos actuales
//        return new Date(currentTimeMillis);
//    }
}
