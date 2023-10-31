package org.app.administrador.Entities.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MantenimientoDTO {
    private Long idMonopatin;

    public Long getIdMantenimiento() {
        return idMonopatin;
    }

    public void setIdMantenimiento(Long idMantenimiento) {
        this.idMonopatin = idMantenimiento;
    }
}

