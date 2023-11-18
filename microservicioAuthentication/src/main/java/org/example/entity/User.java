package org.example.entity;

import jakarta.persistence.*;
import org.example.service.dto.user.request.UserRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column( nullable = false )
    private String nombre;
    @Column( nullable = false )
    private String apellido;
    @Column( nullable = false )
    private String email;
    @Column( nullable = false )
    private String password;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "rel_user__account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> cuentas;

    @ManyToOne
    @JoinColumn(name = "name")
    private Authority rol;

    public User(UserRequestDTO request) {
        this.nombre = request.getNombre();
        this.apellido = request.getApellido();
        this.email = request.getEmail();
    }

    public void setCuentas( Collection<Account> cuentas ){
        this.cuentas = new HashSet<>( cuentas );
    }

    public void setRol ( Authority rol ){
        this.rol = rol;
    }

    public Collection<Object> getAuthorities() {
    }
}