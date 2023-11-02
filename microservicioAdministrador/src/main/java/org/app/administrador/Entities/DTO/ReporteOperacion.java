package org.app.administrador.Entities.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReporteOperacion {
    private Long mantenimiento;
    private Long operacion;
}
