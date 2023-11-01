package org.app.monopatin.entities.DTO;

import lombok.Getter;
import org.app.monopatin.entities.Monopatin;
@Getter
public class MonopatinViajeDTO {

    private Long cantViajes;
    private Monopatin monopatin;
    public MonopatinViajeDTO(Long cantViajes, Monopatin m){
        this.cantViajes=cantViajes;
        this.monopatin=m;
    }
}
