package org.app.usuarios.services;

import org.app.usuarios.entities.Monopatin;
import org.app.usuarios.entities.Parada;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("usuarios")
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario addUsuario(Usuario u) {
        return usuarioRepository.save(u);
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
}
