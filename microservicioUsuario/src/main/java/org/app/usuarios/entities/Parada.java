package org.app.usuarios.entities;

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
    private double latitud;
    @Column
    private double longitud;

    public Parada(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public Parada() {
    }
}