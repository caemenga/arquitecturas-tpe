package org.app.monopatin.entities.DTO;

import lombok.*;

import java.io.Serializable;

//@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public interface ReporteMonopatin extends Serializable {
    Long getMantenimiento();
    Long getOperacion();


}
