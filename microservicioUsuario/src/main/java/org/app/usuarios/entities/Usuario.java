package org.app.usuarios.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private long telefono;
    @Column
    private String email;
    @Column //admin, mantenimiento, cliente
    private String rol;
    @ManyToMany(mappedBy = "usuarios")
    private List<Cuenta> cuentas;

    public Usuario(String nombre, String apellido, long telefono, String email, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
        this.cuentas = new ArrayList<>();
    }

    public Usuario() {
    }
}
