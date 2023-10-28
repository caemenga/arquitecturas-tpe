package org.app.parada.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private long latitud;
    @Column
    private long altitud;

    public Parada(long latitud, long altitud) {
        this.latitud = latitud;
        this.altitud = altitud;
    }

    public Parada() {
    }
}
