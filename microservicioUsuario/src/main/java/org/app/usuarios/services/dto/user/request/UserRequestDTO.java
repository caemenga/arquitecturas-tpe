package org.app.usuarios.services.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.app.usuarios.entities.Authority;
import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.entities.Usuario;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true )
@Getter
@Setter
public class UserRequestDTO implements Serializable {

    private String nombre;
    private String apellido;
    private String email;
    private long telefono;
    private String password;
    private Set<Long> cuentas; //[1,2,3]
    private Set<String> roles; //[admin, user]

    public UserRequestDTO(String nombre, String apellido, String email, long telefono, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.cuentas = new HashSet<>();
        this.roles = new HashSet<>();
    }


//    {
//        "nombre": "tudaiaaaa213123213a",
//        "apellido": "sistemas",
//        "email": "asdad123@tudai.com",
//        "telefono": 2494000000,
//        "password": "hola12345",
//        "cuentas": [1],
//        "roles": ["ADMIN"]
//    }
    public UserRequestDTO(String nombre, String apellido, String email, long telefono, String password, Set<Long> c, Set<String> rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.cuentas = new HashSet<>(c);
        this.roles = new HashSet<>(rol);
    }
//    public void setCuentas(Set<Cuenta> cuentas) {
//        this.cuentas = new HashSet<>(cuentas);
//    }
//
//    public void setAutoridades(Set<Authority> autoridades) {
//        this.roles = new HashSet<>(autoridades);
//    }
}

//{
//    "nobmre": agustin,
//    "cuentas":
//}
