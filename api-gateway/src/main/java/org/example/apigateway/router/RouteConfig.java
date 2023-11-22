package org.example.apigateway.router;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.apigateway.security.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class RouteConfig {

    private Dotenv env;

    public RouteConfig(){
        this.env = Dotenv.load();
    }

    @Bean
    public RouteLocator routes( RouteLocatorBuilder builder, AuthenticationFilter authFilter) {
        return builder.routes()
                //AUTH
                .route("auth", r -> r.path("/api/**")
                        .filters( f ->
                            f.filter(authFilter.apply(new AuthenticationFilter.Config()))
                        )
                        .uri(this.env.get("USUARIO_URL")))
                //MONOPATINES
                .route("monopatin", r -> r.path("/monopatines/**" )
                        .filters( f -> f.filters(
                        authFilter.apply(new AuthenticationFilter.Config())))
                        .uri(this.env.get("MONOPATIN_URL")))
                .route("tarifas", r -> r.path("/tarifas/**" )
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri(this.env.get("MONOPATIN_URL")))
                .route("viajes", r -> r.path( "/viajes/**" )
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri(this.env.get("MONOPATIN_URL")))
                //MANTENIMIENTO
                .route("mantenimiento", r -> r.path("/mantenimiento/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri(this.env.get("MANTENIMIENTO_URL")))
                //ADMINISTRADOR
                .route("administracion", r -> r.path("/administracion/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri(this.env.get("ADMINISTRADOR_URL")))
                //PARADA
                .route("paradas", r -> r.path("/paradas/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri(this.env.get("PARADAS_URL")))
                //USUARIOS
                .route("usuario", r -> r.path("/usuarios/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri(this.env.get("USUARIO_URL")))
                .route("cuentas", r -> r.path("/cuentas/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri(this.env.get("USUARIO_URL")))
                .build();
    }

}
