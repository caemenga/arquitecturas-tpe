package org.app.viajes.models.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.app.viajes.models.DTO.Monopatin;
import org.app.viajes.models.DTO.Usuario;

import java.util.Date;

@Entity
@Data
@Getter
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Usuario usuario;
    @Column
    private Monopatin monopatin;
    @Column
    private long tiempoViaje;
    @Column
    private Date fechaInicio;
    @Column
    private Date fechaFin;
    @Column
    private long KmRecorridos;




}
