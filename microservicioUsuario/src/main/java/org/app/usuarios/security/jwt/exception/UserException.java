package org.app.usuarios.security.jwt.exception;

import lombok.Getter;
import org.app.usuarios.services.user.EnumUserException;

@Getter
public class UserException extends RuntimeException {

    private final EnumUserException  code;
    private final String message;

    public UserException(EnumUserException code, String message ){
        this.code = code;
        this.message = message;
    }
}