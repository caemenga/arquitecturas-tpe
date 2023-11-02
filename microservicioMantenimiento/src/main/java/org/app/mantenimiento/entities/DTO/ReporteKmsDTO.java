package org.app.mantenimiento.entities.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteKmsDTO {
    private Long idMonopatin;
    private Double kmsTotales;
}
