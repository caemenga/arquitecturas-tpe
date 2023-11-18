package org.app.usuarios.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.app.usuarios.services.dto.user.request.UserRequestDTO;

import java.io.Serializable;
import java.util.*;

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
    private String password;
    @Column
    private String email;

    @ManyToMany()
    @JoinTable(name = "usuario_cuenta", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "cuenta_id"))
    private Set<Cuenta> cuentas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_autoridad", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "autoridad_id"))
    private Set<Authority> autoridades;

    public Usuario(String nombre, String apellido, long telefono, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public Usuario(UserRequestDTO request) {
        this.nombre = request.getNombre();
        this.apellido = request.getApellido();
        this.email = request.getEmail();
    }

//
    public void setCuentas(Collection<Cuenta> cuentas) {
        this.cuentas = new HashSet<>(cuentas);
    }
//
    public void setAutoridades(Collection<Authority> autoridades) {
        this.autoridades = new HashSet<>(autoridades);
    }

}
