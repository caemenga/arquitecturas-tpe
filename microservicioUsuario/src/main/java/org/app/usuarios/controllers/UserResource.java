package org.app.usuarios.controllers;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.security.jwt.JWTFilter;
import org.app.usuarios.security.jwt.TokenProvider;
import org.app.usuarios.services.UsuarioService;
import org.app.usuarios.services.dto.AuthRequestDTO;
import org.app.usuarios.services.dto.user.request.UserRequestDTO;
import org.app.usuarios.services.dto.user.response.UserResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api" )
@RequiredArgsConstructor
public class UserResource {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UsuarioService userService;

    /**
     * Valida el token y devuelve un JSON con nombre de usuario y sus autoridades.
     */
    @GetMapping("/validate")
    public ResponseEntity<ValidateTokenDTO> validateGet() {
        final var user = SecurityContextHolder.getContext().getAuthentication();
        final var authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(
                ValidateTokenDTO.builder()
                        .username( user.getName() )
                        .authorities( authorities )
                        .isAuthenticated( true )
                        .build()
        );
    }
    @Data
    @Builder
    public static class ValidateTokenDTO {
        private boolean isAuthenticated;
        private String username;
        private List<String> authorities;
    }

    // INICIAR SESION
//    {
//        "email": "asdad@tudai.com",
//        "password": "hola123",
//    }
    @PostMapping("/login")
    public ResponseEntity<JWTToken> authenticate( @Valid @RequestBody AuthRequestDTO request ) {
        System.out.println("creando login con " + request.toString());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() );

        System.out.println("auth " + authenticationToken.toString());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("paso 2");
        final var jwt = tokenProvider.createToken (authentication );

        System.out.println("paso 3");
        System.out.println(jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt );

        System.out.println("paso 4");
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

//        UserRequestDTO
//{
//    "nombre": "tudai",
//        "apellido": "sistemas",
//        "email": "asdad@tudai.com",
//        "telefono": 2494000000,
//        "password": "hola1234",
//        "rol": "ADMIN"
//}
    @PostMapping("/register")
    //@PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public ResponseEntity<?> register(@Valid @RequestBody UserRequestDTO request ){
        final var newUser = this.userService.createUser( request );
        if (newUser != null){
            return ResponseEntity.ok(newUser);
        } else {
            return ResponseEntity.badRequest().body("Hay un error de sintaxis en el body");
        }
    }

    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }

}