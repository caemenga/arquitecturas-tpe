package org.app.administrador.Entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Long id;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
