package com.example.apigateway.router;

import com.example.apigateway.security.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

//    @Bean
//    public RouteLocator routes( RouteLocatorBuilder builder, AuthenticationFilter authFilter ) {
//        return builder.routes()
//                .route("lll", r -> r.path("/api/authenticate" )
//                        .filters( f ->
//                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
//                        )
//                        .uri("http://localhost:8081"))
//                .route("auth-service", r -> r.path("/api/register" )
//                        .filters( f ->
//                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
//                        )
//                        .uri("http://localhost:8081"))
//                .route("micro-a-product", r -> r.path( "/api/admin/products/**" )
//                        .filters( f ->
//                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
//                        )
//                        .uri("http://localhost:8082"))
//                .route("micro-a-product", r -> r.path("/api/products/**")
//                        .filters(f ->
//                            f.filter(authFilter.apply(new AuthenticationFilter.Config()))
//                        )
//                        .uri("http://localhost:8082"))
//                .build();
//    }

    @Bean
    public RouteLocator routes( RouteLocatorBuilder builder) {
        return builder.routes()
                .route("monopatin", r -> r.path("/monopatines/**" )
                        .uri("http://localhost:8082"))
                .route("tarifa", r -> r.path("/tarifas/**" )
                        .uri("http://localhost:8082"))
                .route("viaje", r -> r.path( "/viajes/**" )
                        .uri("http://localhost:8082"))
                .route("mantenimiento", r -> r.path("/mantenimiento/**")
                        .uri("http://localhost:8085"))
                .route("administracion", r -> r.path("/administracion/**")
                        .uri("http://localhost:8080"))
                .route("parada", r -> r.path("/paradas/**")
                        .uri("http://localhost:8083"))
                .route("cuentas", r -> r.path("/cuentas/**")
                        .uri("http://localhost:8081"))
                .route("usuario", r -> r.path("/usuarios/**")
                        .uri("http://localhost:8081"))
                .route("autenticacion", r -> r.path("/seguridad/autenticacion")//acomodar puerto
                        .uri("http://localhost:8081"))
                .build();
    }
}
