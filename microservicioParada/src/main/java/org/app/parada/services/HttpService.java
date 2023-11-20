package org.app.parada.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpService {
    private Dotenv env;

    public HttpService(){
        this.env = Dotenv.load();
    }

    //GET
    public ResponseEntity<?> getRequest(String token, String url){
        //cargamos los headers junto con el token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        //llamada GET al api gateway + url pasada por parametro
        RestTemplate r = new RestTemplate();

        ResponseEntity<?> response = r.exchange(
                this.env.get("API_GATEWAY_URL") + url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        headers.setContentType(MediaType.APPLICATION_JSON);

        //devolver response
        System.out.println(response.getBody());
        return response;
    }

    //POST
    public ResponseEntity<?> postRequest(String token, String url, Object t){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<?> requestEntity = new HttpEntity<>(t, headers);

        RestTemplate r = new RestTemplate();

        ResponseEntity<?> response = r.exchange(
                this.env.get("API_GATEWAY_URL") + url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        headers.setContentType(MediaType.APPLICATION_JSON);
        return response;
    }

    //PUT
    public ResponseEntity<?> putRequest(String token, String url){
        RestTemplate r = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<?> response = r.exchange(
                this.env.get("API_GATEWAY_URL") + url,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );

        headers.setContentType(MediaType.APPLICATION_JSON);
        return response;
    }

    public String getSecretKey(){
        return this.env.get("SECRET_KEY");
    }

}
