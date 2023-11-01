package org.app.usuarios.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Cuenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date fechaAlta;
    @Column
    private double saldo;
    @Column
    private boolean habilitada;
    @ManyToMany
    private List<Usuario> usuarios;

    public Cuenta(Date fechaAlta, double saldo) {
        this.fechaAlta = fechaAlta;
        this.saldo = saldo;
        this.habilitada = true;
        this.usuarios = new ArrayList<>();
    }

    public Cuenta() {
    }
}
