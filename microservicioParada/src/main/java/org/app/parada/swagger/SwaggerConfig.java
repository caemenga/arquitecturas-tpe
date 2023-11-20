package org.app.parada.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition
@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info().title("MONOPATINES")
                .version("1.0-SNAPSHOT").description("APP DE MONOPATINES"));

    }

}

//import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//        import springfox.documentation.builders.PathSelectors;
//        import springfox.documentation.builders.RequestHandlerSelectors;
//        import springfox.documentation.spi.DocumentationType;
//        import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.app.usuarios.swagger"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//}
