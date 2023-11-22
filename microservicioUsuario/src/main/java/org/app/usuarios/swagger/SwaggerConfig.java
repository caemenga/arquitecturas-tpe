package org.app.usuarios.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Agustin"
                ),
                description = "documentacion oficial Arqui WEB",
                title = "App Monopatines",
                version = "1.0"
        )
)
@Configuration
public class SwaggerConfig {


//    @Bean
//    public OpenAPI api(){
//        return new OpenAPI().info(new Info().title("MONOPATINES")
//                .version("1.0-SNAPSHOT").description("APP DE MONOPATINES"));
//
//    }

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
