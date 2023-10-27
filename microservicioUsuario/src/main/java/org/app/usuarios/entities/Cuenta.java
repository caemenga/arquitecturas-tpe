package org.app.usuarios.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Cuenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Date fechaAlta;
    @Column
    private long saldo;
    @Column
    private boolean habilitada;
    @Column
    @ManyToMany
    private List<Usuario> usuarios;

    public Cuenta(int id, Date fechaAlta, long saldo, boolean habilitada, List<Usuario> usuarios) {
        this.id = id;
        this.fechaAlta = fechaAlta;
        this.saldo = saldo;
        this.habilitada = habilitada;
        this.usuarios = usuarios;
    }

    public Cuenta() {
    }
}
