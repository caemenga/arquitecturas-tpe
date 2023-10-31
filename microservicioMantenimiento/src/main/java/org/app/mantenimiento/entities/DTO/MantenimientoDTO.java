package org.app.mantenimiento.entities.DTO;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MantenimientoDTO {
    private Long idMantenimiento;

    public Long getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Long idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }
}

