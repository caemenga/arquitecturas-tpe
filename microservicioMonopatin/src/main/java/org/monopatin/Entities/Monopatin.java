package org.monopatin.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Monopatin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long tiempoUso;
    @Column
    private long kmRecorridos;
    @Column
// "en uso" | "en mantenimiento"
    private String estado;


}
