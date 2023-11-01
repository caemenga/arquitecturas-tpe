package org.app.usuarios.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Long id;
    private double saldoAagregar;

    public void setSaldoAagregar(double saldoAagregar) {
        this.saldoAagregar = saldoAagregar;
    }

    public double getSaldoAagregar() {
        return saldoAagregar;
    }


}
