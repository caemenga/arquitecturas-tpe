package org.app.usuarios.services.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.app.usuarios.entities.Authority;
import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.entities.Usuario;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true )
@Getter
@Setter
public class UserRequestDTO {

    private String nombre;
    private String apellido;
    private String email;
    private long telefono;
    private String password;
    private Set<Cuenta> cuentas;
    private Set<Authority> roles;

    public UserRequestDTO(String nombre, String apellido, String email, long telefono, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.cuentas = new HashSet<>();
        this.roles = new HashSet<>();
    }
    public void setCuentas(Collection<Cuenta> cuentas) {
        this.cuentas = new HashSet<>(cuentas);
    }

    public void setAutoridades(Collection<Authority> autoridades) {
        this.roles = new HashSet<>(autoridades);
    }
}



//{
//    "nobmre": agustin,
//    "cuentas":
//}
