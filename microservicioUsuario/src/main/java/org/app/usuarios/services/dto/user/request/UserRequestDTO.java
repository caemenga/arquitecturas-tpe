package org.app.usuarios.services.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.app.usuarios.entities.Authority;
import org.app.usuarios.entities.Usuario;

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
    private Set<Long> cuentas;
    private Set<String> authorities;

    public UserRequestDTO(String nombre, String apellido, String email, long telefono, String password, Set<Long> cuentas,
                          Set<String> authorities) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.cuentas = cuentas;
        this.authorities = authorities;
    }
}

//{
//    "nobmre": agustin,
//    "cuentas":
//}
