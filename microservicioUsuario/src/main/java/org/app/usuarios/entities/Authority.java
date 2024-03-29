package org.app.usuarios.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Authority implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1L;
//    @NotNull
    @Id
    @Column(length = 50, nullable = false)
    private String name;

    public Authority(String name) {
        this.name = name;
    }

}
