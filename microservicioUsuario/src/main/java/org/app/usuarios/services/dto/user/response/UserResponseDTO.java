package org.app.usuarios.services.dto.user.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.entities.Usuario;

@Data
public class UserResponseDTO {

    private final long id;
    private final String nombre;
    private final String apellido;
    private final String email;

    public UserResponseDTO( Usuario user ){
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.email = user.getEmail();
    }

}