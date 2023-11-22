package org.app.administrador.Services;

import io.github.cdimascio.dotenv.Dotenv;
import org.app.administrador.Entities.DTO.MonopatinViajeDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HttpService {
    private Dotenv env;

    public HttpService(){
        this.env = Dotenv.load();
    }

    //GET
    public <T> ResponseEntity<T> getRequest(String token, String url, ParameterizedTypeReference<T> parameterizedTypeReference ){
        //cargamos los headers junto con el token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        //llamada GET al api gateway + url pasada por parametro
        RestTemplate r = new RestTemplate();

        ResponseEntity<T> response = r.exchange(
                this.env.get("API_GATEWAY_URL") + url,
                HttpMethod.GET,
                requestEntity,
                parameterizedTypeReference
        );

        headers.setContentType(MediaType.APPLICATION_JSON);

        //devolver response
        System.out.println(response.getBody());
        return response;
    }

    //POST
    public <T> ResponseEntity<T> postRequest(String token, String url, Object t, ParameterizedTypeReference<T> parameterizedTypeReference){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<T> requestEntity = new HttpEntity<T>((T) t, headers);

        RestTemplate r = new RestTemplate();

        ResponseEntity<T> response = r.exchange(
                this.env.get("API_GATEWAY_URL") + url,
                HttpMethod.POST,
                requestEntity,
                parameterizedTypeReference
        );

        headers.setContentType(MediaType.APPLICATION_JSON);
        return response;
    }

    //PUT
    public <T> ResponseEntity<T> putRequest(String token, String url, ParameterizedTypeReference<T> parameterizedTypeReference){
        RestTemplate r = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<T> response = r.exchange(
                this.env.get("API_GATEWAY_URL") + url,
                HttpMethod.PUT,
                requestEntity,
                parameterizedTypeReference
        );

        headers.setContentType(MediaType.APPLICATION_JSON);
        return response;
    }

    public String getSecretKey(){
        return this.env.get( "SECRET_KEY");
    }

}
