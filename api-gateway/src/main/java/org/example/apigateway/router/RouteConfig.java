package org.example.apigateway.router;

import org.example.apigateway.security.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes( RouteLocatorBuilder builder, AuthenticationFilter authFilter) {
        return builder.routes()
                //AUTH

                .route("auth", r -> r.path("/api/**")
                        .filters( f ->
                            f.filter(authFilter.apply(new AuthenticationFilter.Config()))
                        )
                        .uri("http://localhost:8081"))
                //REGISTER
//                .route("auth", r -> r.path("/api/register")
//                        .filters( f ->
//                                f.filter(authFilter.apply(new AuthenticationFilter.Config()))
//                        )
//                        .uri("http://localhost:8081"))
                //MONOPATINES
                .route("monopatin", r -> r.path("/monopatines/**" )
                        .filters( f -> f.filters(
                        authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8082"))
                .route("tarifas", r -> r.path("/tarifas/**" )
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8082"))
                .route("viajes", r -> r.path( "/viajes/**" )
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8082"))
                //MANTENIMIENTO
                .route("mantenimiento", r -> r.path("/mantenimiento/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8085"))

                //ADMINISTRADOR
                .route("administracion", r -> r.path("/administracion/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8086"))
                //PARADA
                .route("paradaS", r -> r.path("/paradas/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8083"))
                //USUARIOS
                .route("usuario", r -> r.path("/usuarios/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8081"))
                .route("cuentas", r -> r.path("/cuentas/**")
                        .filters( f -> f.filters(
                                authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8081"))
                .build();
    }

}
