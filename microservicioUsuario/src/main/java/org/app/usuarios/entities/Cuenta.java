package org.app.usuarios.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private boolean habilitada = true;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cuentas")
    private Set<Usuario> usuarios;

    public Cuenta(Date fechaAlta, double saldo) {
        this.fechaAlta = fechaAlta;
        this.saldo = saldo;
    }

    public Cuenta() {
    }
}
