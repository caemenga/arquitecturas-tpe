package org.app.administrador.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta extends Entity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date fechaAlta;
    @Column
    private float saldo;
    @Column
    private boolean habilitada;
    @ManyToMany
    private List<Usuario> usuarios;

    public Cuenta(Date fechaAlta, float saldo) {
        this.fechaAlta = fechaAlta;
        this.saldo = saldo;
        this.habilitada = true;
        this.usuarios = new ArrayList<>();
    }


}
