package org.app.usuarios.services;


import lombok.RequiredArgsConstructor;
import org.app.usuarios.*;

import org.app.usuarios.entities.*;
import org.app.usuarios.repositories.AuthorityRepository;
import org.app.usuarios.repositories.CuentaRepository;
import org.app.usuarios.repositories.UsuarioRepository;
import org.app.usuarios.security.jwt.exception.EnumJWTException;
import org.app.usuarios.security.jwt.exception.UserException;
import org.app.usuarios.services.dto.user.request.UserRequestDTO;
import org.app.usuarios.services.dto.user.response.UserResponseDTO;
import org.app.usuarios.services.user.EnumUserException;
import org.app.usuarios.services.user.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service("usuarios")
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private AuthorityRepository autoridadRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public UserResponseDTO createUser(UserRequestDTO request ) {
        Optional<Usuario> u = this.usuarioRepository.findByEmailIgnoreCase(request.getEmail());
        if (u.isPresent())
        throw new UserException(EnumUserException.already_exist,
                String.format("Ya existe un usuario con email %s", request.getEmail()));
        final var accounts = this.cuentaRepository.findAllById(request.getCuentas());
//        if (accounts.isEmpty())
//            throw new UserException(EnumUserException.invalid_account,
//                    String.format("No se encontro ninguna cuenta con id %s", request.getCuentas().toString()));
       final var authorities = this.autoridadRepository.findAllById(request.getRoles());
//                .stream()
//                .map(string -> this.autoridadRepository.findById(string)
//                        .orElseThrow(() -> new NotFoundException("autoridad", string)))
//                .toList();
//        if (authorities.isEmpty())
//            throw new UserException(EnumUserException.invalid_authorities,
//                    String.format("No se encontro ninguna autoridad con id %s", request.getRoles().toString()));
        final var user = new Usuario(request);
        for(Cuenta c : accounts){
            user.addCuenta(c);
        }
        for(Authority a : authorities){
            user.addAuthorities(a);
        }
        //user.setAutoridades(a);
        final var encryptedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encryptedPassword);
        final var createdUser = this.usuarioRepository.save(user);
        return new UserResponseDTO(createdUser);
    }

    public Optional<Usuario> getById(Long id) {
        return usuarioRepository.findById(id);
    }

    public boolean deleteUsuario(long id) throws Exception {
        try{
            if(usuarioRepository.existsById(id)){
                usuarioRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<?> getMonopatinesCercanos(double latitud, double longitud) {
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        //traer parada
        String url = "http://localhost:8083/paradas/cercana?latitud=" + latitud + "&longitud=" + longitud ;

        ResponseEntity<Parada> responseParada = rest.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>(){}
        );
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(responseParada.getStatusCode().is2xxSuccessful() && responseParada.getBody()!=null && responseParada.hasBody()){
            Parada p = responseParada.getBody();
            System.out.println(p.toString());
            ResponseEntity<List<Monopatin>> responseMonopatin = rest.exchange(
                    "http://localhost:8082/monopatines/parada/" + p.getId(),
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<>(){}
            );
            headers.setContentType(MediaType.APPLICATION_JSON);
            if(responseMonopatin.getStatusCode().is2xxSuccessful() && responseMonopatin.getBody()!=null && !responseMonopatin.getBody().isEmpty()){
                return ResponseEntity.ok(responseMonopatin.getBody());
            }
            return ResponseEntity.ok("no se encontraron monopatines en la parada cercana");
        }
        return ResponseEntity.ok("no se encontraron paradas cercanas");
    }

//    public ResponseEntity<?> addRol(Authority a){
//        usuarioRepository.addRol(a)
//    }

    public ResponseEntity<?> addCuenta(Usuario user){
        this.usuarioRepository.save(user);
        return ResponseEntity.ok(user);
    }

}

