package org.app.usuarios.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Long id;
    private float saldoAagregar;

    public void setSaldoAagregar(float saldoAagregar) {
        this.saldoAagregar = saldoAagregar;
    }

    public float getSaldoAagregar() {
        return saldoAagregar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
