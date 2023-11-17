package org.app.parada.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
@Document(collection = "paradas")
@Data
@Getter
@Setter
public class Parada {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private double latitud;
    @Column
    private double longitud;

    public Parada(String id, double latitud, double longitud) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Parada() {
    }
}
