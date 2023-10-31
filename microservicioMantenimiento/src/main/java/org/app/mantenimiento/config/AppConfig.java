package org.app.mantenimiento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean("mantenimientoClienteRest")
    public RestTemplate registrarRestTemplate(){
        return new RestTemplate();
    }
}
