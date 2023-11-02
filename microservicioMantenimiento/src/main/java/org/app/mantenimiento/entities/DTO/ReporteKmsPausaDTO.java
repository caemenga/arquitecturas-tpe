package org.app.mantenimiento.entities.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteKmsPausaDTO {
    private Integer idMonopatin;
    private Double kmsTotales;
    private Integer tiempoPausa;
}
