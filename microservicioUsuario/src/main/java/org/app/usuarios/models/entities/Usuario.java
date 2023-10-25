package org.app.usuarios.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private long saldo;
    @Column
    private long telefono;
    @Column
    private String email;
    @Column
    private Date fechaAlta;

    public Usuario(String nombre, String apellido, long saldo, long telefono, String email, Date fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.telefono = telefono;
        this.email = email;
        this.fechaAlta = fechaAlta;
    }

    public Usuario() {
    }


}
