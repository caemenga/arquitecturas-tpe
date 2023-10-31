package org.app.administrador;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class MicroservicioAdministradorApplication {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        SpringApplication.run(MicroservicioAdministradorApplication.class, args);
    }

//    @Bean("monopatinClienteRest")
//    public RestTemplate monopatinClienteRest(){
//        return new RestTemplate();
//    }
//
//    @Bean("mantenimientoClienteRest")
//    public RestTemplate mantenimientoClienteRest(){
//        return new RestTemplate();
//    }
}